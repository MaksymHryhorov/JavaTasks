package com.knubisoft.tasks.algorithm.reflection;

import com.knubisoft.tasks.algorithm.ModelRoot;
import com.knubisoft.tasks.algorithm.ModelRoot.Item;
import lombok.SneakyThrows;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;


public class FieldUtilsImpl implements FieldUtils {

    @Override
    public Field getField(Class<?> cls, String fieldName) throws NoSuchFieldException {
        if (fieldName.trim().isEmpty() || cls == null) {
            throw new NullPointerException();
        }

        return cls.getDeclaredField(fieldName);
    }

    @SneakyThrows
    @Override
    public Field getField(Class<?> cls, String fieldName, boolean forceAccess) {
        if (fieldName.trim().isEmpty() || cls == null) {
            throw new NullPointerException();
        }

        Item item = new Item(12, "type", "name", 12.2, new ModelRoot.Batters(), new ArrayList<>());
        Field field = cls.getDeclaredField(fieldName);
        field.setAccessible(forceAccess);

        if (field.getType().getName().equals("int")) {
            field.set(item, 1);
        } else {
            field.set(item, "TestVALUE");
        }

        return field;
    }

    @Override
    public Field getDeclaredField(Class<?> cls, String fieldName) throws NoSuchFieldException {
        if (fieldName.trim().isEmpty() || cls == null) {
            throw new NullPointerException();
        }

        return cls.getDeclaredField(fieldName);
    }

    @Override
    public Field[] getAllFields(Class<?> cls) {
        if (cls == null) {
            throw new NullPointerException();
        }

        return cls.getFields();
    }

    @SneakyThrows
    @Override
    public Field[] getFieldsWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationCls) {
        if (annotationCls == null || cls == null) {
            throw new NullPointerException();
        }

        Field[] fields = cls.getDeclaredFields();
        Field[] withAnnotation = new Field[10];

        int count = 0;
        for (Field field : fields) {
            if (field.getAnnotations().length == 0) {
                break;
            }
            if (field.getAnnotations()[0].toString().equals("@" + annotationCls.getName() + "()")) {
                withAnnotation[count] = field;
                count++;
            }
        }

        return withAnnotation;
    }
}