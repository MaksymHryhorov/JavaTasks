package com.knubisoft.base.validation.action;

import com.knubisoft.base.validation.excpetion.ExceptionHandler;
import com.knubisoft.base.validation.wrapper.MultipleKeyWrapper;

import java.util.Map;

public interface Case {
    Case action(Map<MultipleKeyWrapper, Object> annotationValue,
                Map<Object, Object> fieldObjectMap, MultipleKeyWrapper multipleKeyWrapper) throws ExceptionHandler;
}