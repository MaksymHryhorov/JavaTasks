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

     public static final String TYPE_JSON = "json";
     public static final String TYPE_CSV = "csv";
     public static final String TYPE_XML = "xml";

    @SneakyThrows
    public List<Person> transformType(File file, TypeReference<List<Person>> objectTypeReference) {
        if (getFileExtension(file).equals(TYPE_JSON)) {
            JSONOrm jsonOrm = new JSONOrm();

            return jsonOrm.transformJson(file, objectTypeReference);
        } else if (getFileExtension(file).equals(TYPE_CSV)) {
            InputStream stream = Main.class.getClassLoader().getResourceAsStream(file.getName());

            List<String> lines = IOUtils.readLines(stream, StandardCharsets.UTF_8);

            return CSVOrm.transform(lines, Person.class);
        } else if (getFileExtension(file).equals(TYPE_XML)) {
            XMLOrm xmlOrm = new XMLOrm();

            return xmlOrm.transformOrm(file, objectTypeReference);
        } else {
            throw new CustomException("File not supported");
        }

    }

    @SneakyThrows
    public String getFileExtension(File file) {
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

    public static void main(String[] args) {
        int x = 1;
        for (int i = 0; i < 5; i++) {
            if (i == 2){ continue; }
            if (i == 4) { break;}
            x += (i < 2 ? i : 2*i);
        }
        System.out.println(x);

        int[][] x1 = {{3,1,4},{1,5,9}};
        int[] y = {2,6,7};

        y = x1[1];
        y[1] = 1;

        System.out.println(x1[0][1] + x1[1][1]);


        Integer numA = 0;
        incrementNumber(numA);
        numA = numA * 2;
        System.out.println(numA);
    }

    private static void incrementNumber(Integer numP) {
        numP++;
        Integer numA = 42;
    }
}
