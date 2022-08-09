package com.knubisoft.ORM;

import com.fasterxml.jackson.core.type.TypeReference;
import com.knubisoft.ORM.format.DBOrm;
import com.knubisoft.ORM.model.Person;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //File file = new File("D:\\Projects\\java-education\\src\\main\\resources\\format.xml");
        File file = new File("D:\\Projects\\java-education\\src\\main\\resources\\sample.csv");
        //File file = new File("D:\\Projects\\java-education\\src\\main\\resources\\format.json");

        List<Person> personList = Proxy.transformType(file, new TypeReference<>() {});

        List<Person> personListFromDataBase = DBOrm.createPerson();

    }

}
