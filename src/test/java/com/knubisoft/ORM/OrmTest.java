package com.knubisoft.ORM;

import com.fasterxml.jackson.core.type.TypeReference;
import com.knubisoft.ORM.format.DBOrm;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrmTest {

    @Test
    @SneakyThrows
    void transformType() {
        DBOrm dbOrm = new DBOrm();
        Proxy proxy = new Proxy();

        File xmlFile = new File("D:\\Projects\\java-education\\src\\main\\resources\\format.xml");
        File csvFile = new File("D:\\Projects\\java-education\\src\\main\\resources\\sample.csv");
        File jsonFile = new File("D:\\Projects\\java-education\\src\\main\\resources\\format.json");


        assertEquals("name='Ivan', age=19, salary=1000, position='junior', dateOfBirth=1995-10-19, xxx=1234.123",
                proxy.transformType(xmlFile, new TypeReference<>() {}).get(1).toString());
        assertEquals("name='Misha', age=21, salary=3000, position='senior', dateOfBirth=1998-10-23, xxx=1234.123",
                proxy.transformType(xmlFile, new TypeReference<>() {}).get(3).toString());
        assertEquals("name='Sonia', age=23, salary=5000, position='middle', dateOfBirth=1989-08-18, xxx=1234.123",
                proxy.transformType(xmlFile, new TypeReference<>() {}).get(5).toString());

        assertEquals("name='Ivan', age=19, salary=1000, position='junior', dateOfBirth=1995-10-19, xxx=1234.123",
                proxy.transformType(csvFile, new TypeReference<>() {}).get(1).toString());
        assertEquals("name='Misha', age=21, salary=3000, position='senior', dateOfBirth=1987-10-23, xxx=1234.123",
                proxy.transformType(csvFile, new TypeReference<>() {}).get(3).toString());
        assertEquals("name='Sonia', age=23, salary=4000, position='middle', dateOfBirth=1989-08-18, xxx=1234.123",
                proxy.transformType(csvFile, new TypeReference<>() {}).get(5).toString());

        assertEquals("name='Ivan', age=19, salary=1000, position='junior', dateOfBirth=1995-10-19, xxx=1234.123",
                proxy.transformType(jsonFile, new TypeReference<>() {}).get(1).toString());
        assertEquals("name='Misha', age=21, salary=3000, position='senior', dateOfBirth=1998-10-23, xxx=1234.123",
                proxy.transformType(jsonFile, new TypeReference<>() {}).get(3).toString());
        assertEquals("name='Sonia', age=23, salary=5000, position='middle', dateOfBirth=1989-08-18, xxx=1234.123",
                proxy.transformType(jsonFile, new TypeReference<>() {}).get(5).toString());

        assertEquals("name='Ivan', age=19, salary=1000, position='junior', dateOfBirth=1995-10-19, xxx=1234.12",
                dbOrm.createPerson().get(1).toString());
        assertEquals("name='Misha', age=21, salary=3000, position='senior', dateOfBirth=1987-10-23, xxx=1234.12",
                dbOrm.createPerson().get(3).toString());
        assertEquals("name='Sonia', age=23, salary=4000, position='middle', dateOfBirth=1989-08-18, xxx=1234.12",
                dbOrm.createPerson().get(5).toString());
    }
}
