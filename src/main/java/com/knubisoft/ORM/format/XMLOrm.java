package com.knubisoft.ORM.format;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.util.List;

public class XMLOrm {

    @SneakyThrows
    public <T> List<T> transformOrm(File path, TypeReference<List<T>> typeReference) {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.findAndRegisterModules();

        List<T> xmlList = xmlMapper.readValue(path, typeReference);

        return xmlList;
    }
}
