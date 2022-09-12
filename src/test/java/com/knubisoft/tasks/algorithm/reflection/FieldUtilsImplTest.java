package com.knubisoft.tasks.algorithm.reflection;


import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FieldUtilsImplTest {
    private final FieldUtils fieldUtils = new FieldUtilsImpl();

    @Test
    @SneakyThrows
    void getField() {
        Class<?> cls = Class.forName("com.knubisoft.tasks.algorithm.ModelRoot$Item");

        assertThrows(NullPointerException.class, () -> fieldUtils.getField(cls, ""));
        assertThrows(NullPointerException.class, () -> fieldUtils.getField(cls, "         "));
        assertThrows(NullPointerException.class, () -> fieldUtils.getField(null, "name"));
        assertThrows(NoSuchFieldException.class, () -> fieldUtils.getField(cls, "failName"));

        assertEquals(cls.getDeclaredField("name"), fieldUtils.getField(cls, "name"));
        assertEquals(cls.getDeclaredField("topping"), fieldUtils.getField(cls, "topping"));

        assertThrows(NullPointerException.class, () -> fieldUtils.getField(cls, "", false));
        assertThrows(NullPointerException.class, () -> fieldUtils.getField(cls, "         ", false));
        assertThrows(NullPointerException.class, () -> fieldUtils.getField(null, "name", false));
        assertThrows(NoSuchFieldException.class, () -> fieldUtils.getField(cls, "failName", false));

        assertEquals(cls.getDeclaredField("id"), fieldUtils.getField(cls, "id", true));
        assertEquals(cls.getDeclaredField("name"), fieldUtils.getField(cls, "name", true));

    }

    @SneakyThrows
    @Test
    void getDeclaredField() {
        Class<?> cls = Class.forName("com.knubisoft.tasks.algorithm.ModelRoot$Item");

        assertThrows(NullPointerException.class, () -> fieldUtils.getDeclaredField(cls, ""));
        assertThrows(NullPointerException.class, () -> fieldUtils.getDeclaredField(cls, "         "));
        assertThrows(NullPointerException.class, () -> fieldUtils.getDeclaredField(null, "name"));
        assertThrows(NoSuchFieldException.class, () -> fieldUtils.getDeclaredField(cls, "failName"));

        assertEquals(cls.getDeclaredField("name"), fieldUtils.getField(cls, "name"));
        assertEquals(cls.getDeclaredField("topping"), fieldUtils.getField(cls, "topping"));
    }

    @Test
    @SneakyThrows
    void getAllFields() {
        Class<?> cls = Class.forName("com.knubisoft.tasks.algorithm.ModelRoot$Item");
        Field[] fields = cls.getDeclaredFields();

        assertThrows(NullPointerException.class, () -> fieldUtils.getAllFields(null));

        assertEquals(fields[0], fieldUtils.getAllFields(cls)[0]);
        assertEquals(fields[3], fieldUtils.getAllFields(cls)[3]);
        assertEquals(fields[5], fieldUtils.getAllFields(cls)[5]);

    }

    @Test
    @SneakyThrows
    void getFieldsWithAnnotation() {
        Class<?> cls = Class.forName("com.knubisoft.tasks.algorithm.ModelRoot$Item");
        Class<? extends Annotation> annotationCls = AnnotationTest.class;
        Field[] fields = fieldUtils.getFieldsWithAnnotation(cls, annotationCls);

        assertThrows(NullPointerException.class, () -> fieldUtils.getFieldsWithAnnotation(null, null));
        assertThrows(NullPointerException.class, () -> fieldUtils.getFieldsWithAnnotation(cls, null));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            Field[] fieldsTest = fieldUtils.getFieldsWithAnnotation(cls, annotationCls);
            Field wrongLength = fieldsTest[fields.length + 10];
        });

        assertEquals(fields[0], fieldUtils.getFieldsWithAnnotation(cls, annotationCls)[0]);
        assertEquals(fields[1], fieldUtils.getFieldsWithAnnotation(cls, annotationCls)[1]);
    }
}
