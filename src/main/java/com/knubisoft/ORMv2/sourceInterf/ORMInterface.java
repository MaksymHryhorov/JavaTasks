package com.knubisoft.ORMv2.sourceInterf;

import lombok.SneakyThrows;

import java.util.List;

public interface ORMInterface {

    @SneakyThrows
    <T> List<T> readAll(DataReadWriteSource<?> source, Class<T> cls);
}
