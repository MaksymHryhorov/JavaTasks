package com.knubisoft.ORMv2;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.function.Function;

public class ConnectionToDatabase {

    @SneakyThrows
    public void withConnection(Function<Connection, Void> function) {
        try (Connection c = DriverManager.getConnection("jdbc:sqlite:sample.db")) {
            try (Statement stmt = c.createStatement()) {
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS person " +
                        "(id INTEGER not NULL, " +
                        " name VARCHAR(255), " +
                        " position VARCHAR(255), " +
                        " age INTEGER, " +
                        " PRIMARY KEY ( id ))");

                stmt.executeUpdate("DELETE FROM person");

                stmt.executeUpdate("INSERT INTO person (name, position, age) VALUES ('MyName5','DEVELOPER','5')");
                stmt.executeUpdate("INSERT INTO person (name, position, age) VALUES ('MyName6','DEVELOPER','6')");
                stmt.executeUpdate("INSERT INTO person (name, position, age) VALUES ('MyName7','DEVELOPER','7')");
                stmt.executeUpdate("INSERT INTO person (name, position, age) VALUES ('MyName8','DEVELOPER','8')");
                stmt.executeUpdate("INSERT INTO person (name, position, age) VALUES ('MyName9','DEVELOPER','9')");
                stmt.executeUpdate("INSERT INTO person (name, position, age) VALUES ('MyName10','DEVELOPER','10')");

            }
            function.apply(c);
        }
    }

}
