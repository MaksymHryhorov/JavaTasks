//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.knubisoft.tasks.algorithm.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Add implementation class and tests
 */
public interface FieldUtils {

    Field getField(Class<?> cls, String fieldName) throws NoSuchFieldException;

    Field getField(Class<?> cls, String fieldName, boolean forceAccess) throws NoSuchFieldException, IllegalAccessException;

    Field getDeclaredField(Class<?> cls, String fieldName) throws NoSuchFieldException;

    Field[] getAllFields(Class<?> cls);

    Field[] getFieldsWithAnnotation(Class<?> cls, Class<? extends Annotation> annotationCls);

}
