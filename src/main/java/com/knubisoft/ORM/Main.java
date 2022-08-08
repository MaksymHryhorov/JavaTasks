package com.knubisoft.ORM;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStream stream = Main.class.getClassLoader().getResourceAsStream("sample.csv");
        List<String> lines = IOUtils.readLines(stream, StandardCharsets.UTF_8);

        List<Person> personList = CSVOrm.transform(lines, Person.class);


        String path = "D:\\Projects\\java-education\\src\\main\\resources\\format.json";

        List<Person> jsonList2 = CSVOrm.transformJson(path, new TypeReference<>() {});


    }

}
