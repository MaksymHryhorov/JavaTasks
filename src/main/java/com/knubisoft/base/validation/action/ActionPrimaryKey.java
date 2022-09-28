package com.knubisoft.base.validation.action;

import com.knubisoft.base.validation.wrapper.MultipleKeyWrapper;

import java.util.Map;

public class ActionPrimaryKey implements Case {

    @Override
    public Case action(Map<MultipleKeyWrapper, Object> annotationValue, Map<Object, Object> fieldObjectMap,
                       MultipleKeyWrapper multipleKeyWrapper) {
        Object key = multipleKeyWrapper.getKey();
        Object additionalKey = multipleKeyWrapper.getAdditionalKey();

        Object value = fieldObjectMap.get(key);

        return null;
    }
}
