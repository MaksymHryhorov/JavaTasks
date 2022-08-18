package com.knubisoft.ORM.format;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.util.List;

public class JSONOrm {

    @SneakyThrows
    public <T> List<T> transformJson(File path, TypeReference<List<T>> typeReference) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        List<T> jsonList = mapper.readValue(path, typeReference);

        return jsonList;
    }
}
