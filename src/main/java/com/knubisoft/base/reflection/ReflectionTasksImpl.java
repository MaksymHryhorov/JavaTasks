package com.knubisoft.base.reflection;

import com.knubisoft.base.reflection.model.Cat;
import com.knubisoft.base.reflection.model.EntryModel;
import com.knubisoft.base.reflection.model.InheritedEntryModel;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ReflectionTasksImpl implements ReflectionTasks {
    private static InheritedEntryModel inheritedEntryModel;
    private static EntryModel entryModel;

    @Override
    public Object createNewInstanceForClass(Class<?> cls) {
        if (cls == null) {
            throw new NoSuchElementException();
        }

        try {

            if (cls != Class.forName(InheritedEntryModel.class.getName())) {
                throw new RuntimeException();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Class clazz = null;
        inheritedEntryModel = null;

        try {
            //clazz = Class.forName("com.knubisoft.base.reflection.model.InheritedEntryModel");

            // Create an array of objects. They correspond to parameters constructor
            Class[] modelClass = {String.class};
            Class[] modelClass2 = {String.class, String.class};
            Class[] modelClass3 = {String.class, String.class, String.class};

            // Transmit parameters to class constructor. Then call newInstance method with right param and don't
            // forget to cast object to the class we need
            inheritedEntryModel = (InheritedEntryModel) cls.getConstructor(modelClass).newInstance("field1");
            //inheritedEntryModel = (InheritedEntryModel) clazz.getConstructor(modelClass).newInstance("field1", "field2");
            //inheritedEntryModel = (InheritedEntryModel) clazz.getConstructor(modelClass).newInstance("field1", "field2", "field3");


        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        return inheritedEntryModel;
    }

    @Override
    public <T> Class<? extends T> findImplementationForInterface(Class<T> cls) {
        if (cls == null) {
            throw new NoSuchElementException();
        }

        try {
            if (cls != Class.forName(EntryModel.class.getName())) {
                throw new IllegalArgumentException();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Set<Class<? extends EntryModel>> reflections = (new Reflections(cls)).getSubTypesOf(EntryModel.class);
        Class<? extends T> implementClass = null;

        for (Class<? extends EntryModel> element : reflections) {
            implementClass = (Class<? extends T>) element;
        }

        return implementClass;
    }

    @Override
    public Map<String, Object> findAllFieldsForClass(Class<?> cls) {
        if (cls == null) {
            throw new NoSuchElementException();
        }

        Map<String, Object> objectMap = new HashMap<>();

        Field[] fields = null;

        if (cls.getSuperclass() != null) {
            fields = cls.getSuperclass().getDeclaredFields();
        } else {
            fields = cls.getDeclaredFields();
        }

        for (Field field : fields) {
            Class<?> fieldType = field.getType();
            objectMap.put(field.getName(), fieldType.getName());
        }

        return objectMap;
    }

    @Override
    public int countPrivateMethodsInClass(Class<?> cls) {
        if (cls == null) {
            throw new NoSuchElementException();
        }

        Method[] fields = cls.getDeclaredMethods();
        int count = 0;

        for (Method field : fields) {
            int modifier = field.getModifiers();

            if (modifier == 2) {
                count++;
            }

        }

        return count;
    }

    @Override
    public boolean isMethodHasAnnotation(Method method, Class<?> clazz) {
        if (method == null || clazz == null) {
            throw new NoSuchElementException();
        }

        Annotation[] annotations1 = method.getAnnotations();

        if (annotations1.length > 0) {
            return true;
        }

        return false;
    }

    @Override
    public Object evaluateMethodByName(Class<?> cls, String name) {
        if (cls == null || name == null) {
            throw new IllegalArgumentException();
        }

        Method method = null;
        Object returnType = null;
        try {
            method = cls.getMethod(name, null);

            returnType = method.getReturnType();

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return returnType;
    }

    @Override
    public Object evaluateMethodWithArgsByName(Object obj, String name, Object... args) {
        if (obj == null || name == null || args == null) {
            throw new IllegalArgumentException();
        }


        Class path = obj.getClass();

        Object result = "";


        try {
            Method method = path.getMethod(name, String.class);
            result = method.invoke(obj, args);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


        return result;
    }

    @Override
    public Object changePrivateFieldValue(Object instance, String name, Object newValue) {
        if (instance == null || name == null || newValue == null) {
            throw new IllegalArgumentException();
        }

        Class<?> catClass = null;
        Field field = null;
        Object result = null;

        try {
            catClass = instance.getClass();
            field = catClass.getDeclaredField(name);

            field.setAccessible(true);
            field.setInt(instance, (Integer) newValue);

            result = field.get(instance);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return result;
    }
}
