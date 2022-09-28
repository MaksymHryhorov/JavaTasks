package com.knubisoft.base.validation;

import com.knubisoft.base.validation.action.*;
import com.knubisoft.base.validation.annotation.*;
import com.knubisoft.base.validation.excpetion.ExceptionHandler;
import com.knubisoft.base.validation.wrapper.MultipleKeyWrapper;
import lombok.SneakyThrows;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ValidationTasksImpl implements ValidationTasks {

    @Override
    public void validate(Object instance) throws ExceptionHandler {
        Map<MultipleKeyWrapper, Object> annotationValue = getAnnotationClass(instance);
        Map<Object, Object> fieldObjectMap = getValuesFromInstance(instance);

        for (MultipleKeyWrapper multipleKeyWrapper : annotationValue.keySet()) {
            Object additionalKey = multipleKeyWrapper.getAdditionalKey();
            Map<Class<? extends Annotation>, Case> actionMap = new HashMap<>(Map.of(
                    PrimaryKey.class, new ActionPrimaryKey(),
                    MaxLength.class, new ActionMaxLength(),
                    NotNull.class, new ActionNotNull(),
                    Entity.class, new ActionEntity(),
                    ReferClass.class, new ActionReferClass()
            ));

            if (additionalKey instanceof PrimaryKey) {
                actionMap.put(PrimaryKey.class, new ActionPrimaryKey().action(annotationValue, fieldObjectMap, multipleKeyWrapper));
            }
            if (additionalKey instanceof NotNull) {
                actionMap.put(NotNull.class, new ActionNotNull().action(annotationValue, fieldObjectMap, multipleKeyWrapper));
            }
            if (additionalKey instanceof Entity) {
                actionMap.put(Entity.class, new ActionEntity().action(annotationValue, fieldObjectMap, multipleKeyWrapper));
            }
            if (additionalKey instanceof ReferClass) {
                actionMap.put(ReferClass.class, new ActionReferClass().action(annotationValue, fieldObjectMap, multipleKeyWrapper));
            }
            if (additionalKey instanceof MaxLength) {
                actionMap.put(MaxLength.class, new ActionMaxLength().action(annotationValue, fieldObjectMap, multipleKeyWrapper));
            }

        }

    }

    @SneakyThrows
    private Map<Object, Object> getValuesFromInstance(Object instance) {
        Class<?> clazz = instance.getClass();
        Field[] fields = clazz.getDeclaredFields();

        Map<Object, Object> fieldObjectMap = new HashMap<>();

        for (Field field : fields) {
            field.setAccessible(true);
            fieldObjectMap.put(field.getName(), field.get(instance));
        }

        return fieldObjectMap;
    }

    private Map<MultipleKeyWrapper, Object> getAnnotationClass(Object instance) {
        Field[] fields = instance.getClass().getDeclaredFields();
        Map<MultipleKeyWrapper, Object> annotationValue = new HashMap<>();

        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            annotationValue.putAll(getAnnotationValue(annotations, field));
        }

        return annotationValue;
    }

    /**
     * annotation.annotationType().getAnnotationType().members() // second solution
     *
     * @param annotations
     * @param field
     * @return
     */
    private Map<MultipleKeyWrapper, Object> getAnnotationValue(Annotation[] annotations, Field field) {
        Map<MultipleKeyWrapper, Object> objectMap = new HashMap<>();

        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(PrimaryKey.class)) {
                PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
                objectMap.put(new MultipleKeyWrapper(field.getName(), annotation), primaryKey.isPrimary());
            }
            if (annotation.annotationType().equals(NotNull.class)) {
                NotNull notNull = field.getAnnotation(NotNull.class);
                objectMap.put(new MultipleKeyWrapper(field.getName(), annotation), true);

            }
            if (annotation.annotationType().equals(MaxLength.class)) {
                MaxLength maxLength = field.getAnnotation(MaxLength.class);
                objectMap.put(new MultipleKeyWrapper(field.getName(), annotation), maxLength.maxLength());
            }
            if (annotation.annotationType().equals(Entity.class)) {
                Entity entity = field.getAnnotation(Entity.class);
                objectMap.put(new MultipleKeyWrapper(field.getName(), annotation), true);

            }
            if (annotation.annotationType().equals(ReferClass.class)) {
                ReferClass referClass = field.getAnnotation(ReferClass.class);
                objectMap.put(new MultipleKeyWrapper(field.getName(), annotation), referClass.referClass());
            }
        }

        return objectMap;
    }

    @Override
    public User buildUser() {
        return null;
    }

    @Override
    public UserGeneralDetails buildUserGeneralDetails() {
        return null;
    }

    @Override
    public UserAddressDetails buildUserAddressDetails() {
        return null;
    }
}
