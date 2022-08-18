package com.knubisoft.ORMv2.parsingStrategy;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.knubisoft.ORMv2.FileReadWriteSource;
import com.knubisoft.ORMv2.Table;
import lombok.SneakyThrows;

public class XMLParsingStrategy implements ParsingStrategy<FileReadWriteSource> {
    @SneakyThrows
    @Override
    public Table parseToTable(FileReadWriteSource content) {
        XmlMapper mapper = new XmlMapper();
        JsonNode result = mapper.readTree(content.getContent());
        return null;
    }
}
