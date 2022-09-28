package com.knubisoft.base.validation.action;

import com.knubisoft.base.validation.excpetion.ExceptionHandler;
import com.knubisoft.base.validation.wrapper.MultipleKeyWrapper;

import java.util.Map;

public class ActionMaxLength implements Case {
    @Override
    public Case action(Map<MultipleKeyWrapper, Object> annotationValue,
                       Map<Object, Object> fieldObjectMap, MultipleKeyWrapper multipleKeyWrapper) throws ExceptionHandler {
        Object key = multipleKeyWrapper.getKey();

        String value = (String) fieldObjectMap.get(key);
        int maxLength = (int) annotationValue.get(multipleKeyWrapper);

        if (value.length() > maxLength) {
            throw new ExceptionHandler("Field: " + key + " can not be more than " + maxLength);
        }

        return null;
    }
}
