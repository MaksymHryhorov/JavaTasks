package com.knubisoft.base.reflection;

import com.knubisoft.base.reflection.model.Cat;
import com.knubisoft.base.reflection.model.EntryModel;
import com.knubisoft.base.reflection.model.InheritedEntryModel;
import com.knubisoft.base.string.StringTasks;
import com.knubisoft.base.string.StringTasksImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ReflectionTasksTest {

    ReflectionTasks instance = new ReflectionTasksImpl();

    @Test
    @SneakyThrows
    public void createNewInstanceForClassSuccessful() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.model.InheritedEntryModel");
        assertEquals(InheritedEntryModel.class, instance.createNewInstanceForClass(clazz).getClass());
        assertEquals(InheritedEntryModel.class,
                instance.createNewInstanceForClass(InheritedEntryModel.class).getClass());
    }

    @Test
    @SneakyThrows
    public void createNewInstanceForClassFail() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.ReflectionTasks");
        assertThrows(RuntimeException.class, () -> instance.createNewInstanceForClass(clazz));
        assertThrows(RuntimeException.class, () -> instance.createNewInstanceForClass(ReflectionTasks.class));
        assertThrows(NoSuchElementException.class, () -> instance.createNewInstanceForClass(null));
    }

    @Test
    @SneakyThrows
    public void findImplementationForInterfaceSuccessful() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.model.EntryModel");
        assertEquals(InheritedEntryModel.class, instance.findImplementationForInterface(clazz));
        assertEquals(InheritedEntryModel.class, instance.findImplementationForInterface(EntryModel.class));
    }

    @Test
    @SneakyThrows
    public void findImplementationForInterfaceFail() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.ReflectionTasks");
        assertThrows(IllegalArgumentException.class,
                () -> instance.findImplementationForInterface(clazz));
        assertThrows(IllegalArgumentException.class,
                () -> instance.findImplementationForInterface(ReflectionTasks.class));
        assertThrows(NoSuchElementException.class, () -> instance.findImplementationForInterface(null));
    }

    @Test
    @SneakyThrows
    public void findAllFieldsForClassSuccessful() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.model.InheritedEntryModel");
        assertEquals(4, instance.findAllFieldsForClass(clazz).size());
        assertEquals(0, instance.findAllFieldsForClass(ReflectionTasks.class).size());
        assertEquals(0, instance.findAllFieldsForClass(ReflectionTasksImpl.class).size());
        assertEquals(0, instance.findAllFieldsForClass(StringTasks.class).size());
        assertEquals(0, instance.findAllFieldsForClass(StringTasksImpl.class).size());
        assertEquals(4, instance.findAllFieldsForClass(InheritedEntryModel.class).size());
    }

    @Test
    @SneakyThrows
    public void findAllFieldsForClassFail() {
        assertThrows(NoSuchElementException.class, () -> instance.findAllFieldsForClass(null));
    }

    @Test
    @SneakyThrows
    public void countPrivateMethodsInClassSuccessful() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.model.EntryModel");
        assertEquals(2, instance.countPrivateMethodsInClass(clazz));
        assertEquals(0, instance.countPrivateMethodsInClass(StringTasks.class));
        assertEquals(2, instance.countPrivateMethodsInClass(EntryModel.class));
    }

    @Test
    @SneakyThrows
    public void countPrivateMethodsInClassFail() {
        assertThrows(NoSuchElementException.class, () -> instance.countPrivateMethodsInClass(null));
    }

    @Test
    @SneakyThrows
    public void evaluateMethodByNameSuccessful() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.model.EntryModel");
        assertEquals(clazz.getMethod("testMethod").getReturnType(),
                instance.evaluateMethodByName(clazz, "testMethod"));
        assertEquals(clazz.getMethod("test2").getReturnType(),
                instance.evaluateMethodByName(clazz, "test2"));


    }

    @Test
    @SneakyThrows
    public void evaluateMethodByNameFail() {
        assertThrows(IllegalArgumentException.class, () ->
                instance.evaluateMethodByName(null, "test"));
        assertThrows(IllegalArgumentException.class, () ->
                instance.evaluateMethodByName(Class.forName("com.knubisoft.base.reflection.model.EntryModel"), null));
    }

    @Test
    @SneakyThrows
    public void isMethodHasAnnotationSuccessful() {
        Method method1 = EntryModel.class.getMethod("testMethod");
        Method method2 = InheritedEntryModel.class.getMethod("testMethod");

        Class<?> clazz1 = Class.forName("com.knubisoft.base.reflection.model.EntryModel");
        Class<?> clazz2 = Class.forName("com.knubisoft.base.reflection.model.InheritedEntryModel");

        assertFalse(instance.isMethodHasAnnotation(method1, clazz1));
        assertTrue(instance.isMethodHasAnnotation(method2, clazz2));

    }

    @Test
    @SneakyThrows
    public void isMethodHasAnnotationFail() {
        assertThrows(NoSuchElementException.class, () -> instance.isMethodHasAnnotation(null, null));
    }

    @Test
    public void evaluateMethodByNameArgsSuccessful() {
        assertEquals("dlroW ,olleH",
                instance.evaluateMethodWithArgsByName(new StringTasksImpl(), "reverseString", "Hello, World"));
       /* assertEquals("He, Worldllo",
                instance.evaluateMethodWithArgsByName(new StringTasksImpl(), "insertStringInMiddle",
                        "Hello", ", World"));*/
        assertEquals("g",
                instance.evaluateMethodWithArgsByName(new StringTasksImpl(), "uniqueCharacters",
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do"));
    }

    @Test
    public void evaluateMethodByNameArgsFail() {
        assertThrows(IllegalArgumentException.class,
                () -> instance.evaluateMethodWithArgsByName(null, "builder", "arg1", "arg2"));
        assertThrows(IllegalArgumentException.class,
                () -> instance.evaluateMethodWithArgsByName(new StringTasksImpl(), null, "arg1", "arg2"));
        assertThrows(IllegalArgumentException.class,
                () -> instance.evaluateMethodWithArgsByName(new StringTasksImpl(),
                        "insertStringInMiddle", null));
    }

    @Test
    @SneakyThrows
    public void changePrivateFieldValueSuccessful() {
        Class<?> clazz = Class.forName("com.knubisoft.base.reflection.model.Cat");
        Cat cat = new Cat("Bat", 10);

        assertEquals(4,
                instance.changePrivateFieldValue(cat, "age", 4));
        assertEquals(15,
                instance.changePrivateFieldValue(cat, "age", 15));
        assertEquals(11,
                instance.changePrivateFieldValue(cat, "age", 11));

    }

    @Test
    public void changePrivateFieldValueFail() {
        Cat cat = new Cat();

        assertThrows(IllegalArgumentException.class,
                () -> instance.changePrivateFieldValue(null, "test", "test2"));
        assertThrows(IllegalArgumentException.class,
                () -> instance.changePrivateFieldValue(cat, null, "test"));
        assertThrows(IllegalArgumentException.class,
                () -> instance.changePrivateFieldValue(cat, "test", null));
    }
}
