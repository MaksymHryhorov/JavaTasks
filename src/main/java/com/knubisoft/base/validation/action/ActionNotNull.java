package com.knubisoft.base.validation.action;

import com.knubisoft.base.validation.excpetion.ExceptionHandler;
import com.knubisoft.base.validation.wrapper.MultipleKeyWrapper;

import java.util.Map;

public class ActionNotNull implements Case {
    @Override
    public Case action(Map<MultipleKeyWrapper, Object> annotationValue, Map<Object, Object> fieldObjectMap,
                       MultipleKeyWrapper multipleKeyWrapper) throws ExceptionHandler {

        Object key = multipleKeyWrapper.getKey();
        Object value = fieldObjectMap.get(key);

        if (value == null || value.toString().trim().length() == 0) {
            throw new ExceptionHandler("Field: " + key + " can not be null");
        }

        return null;
    }
}
