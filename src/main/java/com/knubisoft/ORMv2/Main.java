package com.knubisoft.ORMv2;

import com.knubisoft.ORMv2.model.Person;
import com.knubisoft.ORMv2.sourceInterf.DataReadWriteSource;
import com.knubisoft.ORMv2.sourceInterf.ORMInterface;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;


public class Main {

    private static final ORMInterface ORM = new ORM();

    public static void main(String[] args) {
        ConnectionToDatabase connection = new ConnectionToDatabase();

        connection.withConnection(conn -> {
            process(conn);
            return null;
        });
    }

    private static void process(Connection connection) {
        //URL url = Main.class.getClassLoader().getResource("format.json");
        WriteToDatabase wtdb = new WriteToDatabase();

        List<Person> result;

        DataReadWriteSource<ResultSet> rw = new ConnectionReadWriteSource(connection);

        result = ORM.readAll(rw, Person.class);
        //result.add(new Person("WRITE", BigInteger.ZERO, BigInteger.ZERO, "WRITE", LocalDate.now(), 0F));

        wtdb.writeToDataBase(result, Person.class);

    }

}
