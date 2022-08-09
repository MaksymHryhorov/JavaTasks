package com.knubisoft.ORM;

import com.fasterxml.jackson.core.type.TypeReference;
import com.knubisoft.ORM.exception.CustomException;
import com.knubisoft.ORM.format.CSVOrm;
import com.knubisoft.ORM.format.JSONOrm;
import com.knubisoft.ORM.format.XMLOrm;
import com.knubisoft.ORM.model.Person;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Proxy {

    @SneakyThrows
    public static List<Person> transformType(File file, TypeReference<List<Person>> objectTypeReference) {
        if (getFileExtension(file).equals("json")) {

            return JSONOrm.transformJson(file, objectTypeReference);
        } else if (getFileExtension(file).equals("csv")) {
            InputStream stream = Main.class.getClassLoader().getResourceAsStream(file.getName());

            List<String> lines = IOUtils.readLines(stream, StandardCharsets.UTF_8);

            return CSVOrm.transform(lines, Person.class);
        } else if (getFileExtension(file).equals("xml")) {

            return XMLOrm.transformJson(file, objectTypeReference);
        }
        else {
            throw new CustomException("File not supported");
        }

    }

    @SneakyThrows
    public static String getFileExtension(File file) {
        String fileName = file.getName();
        // if file name has "."  and it isn't first symbol in file name
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            // Then we cut all symbols to the last dot in file name, xxx.txt -> txt
            return fileName.substring(fileName.lastIndexOf(".") + 1);
            // otherwise, return expansion not found
        else {
            throw new CustomException("File not supported ");
        }
    }
}
