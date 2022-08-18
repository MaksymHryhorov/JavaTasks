package com.knubisoft.ORM.format;

import com.knubisoft.ORM.database.DBConnect;
import com.knubisoft.ORM.model.Person;
import lombok.SneakyThrows;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBOrm {

    @SneakyThrows
    public List<Person> createPerson() {
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        List<Person> list = new ArrayList<>();
        Person person = new Person();

        connection = DBConnect.createNewDBConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM person");


        while (resultSet.next()) {
            person.setName(resultSet.getString("name"));
            person.setAge(BigInteger.valueOf(resultSet.getInt("age")));
            person.setSalary(BigInteger.valueOf(resultSet.getInt("salary")));
            person.setPosition(resultSet.getString("position"));
            person.setDateOfBirth(resultSet.getDate("dateOfBirth").toLocalDate());
            person.setXxx(resultSet.getFloat("xxx"));

            list.add(person);
            person = new Person();
        }


        return list;
    }

}
