package com.knubisoft.ORMv2;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.function.Function;

public class WriteToDatabase {
    @SneakyThrows
    public void writeToDataBase(List<?> list, Class<?> cls) {
        ConnectionToDatabase connection = new ConnectionToDatabase();

        //Object objectToInsert = new Person("TEST", BigInteger.valueOf(10), BigInteger.valueOf(1000), "DEVELOPER", LocalDate.now(), 0F);

        connection.withConnection(new Function<>() {
            @SneakyThrows
            @Override
            public Void apply(Connection connection) {
                for (int i = 0; i < list.size(); i++) {
                    SQLHelper.ModelSQLHelper helper = new SQLHelper.ModelSQLHelper(collectMetaInformation(connection, list.get(i)));
                    String sql = helper.buildSQL(list.get(i));
                    PreparedStatement ps = connection.prepareStatement(sql);
                    helper.bindArguments(list.get(i), ps);
                    ps.execute();
                }

                return null;
            }

            private List<String> collectMetaInformation(Connection connection,
                                                        Object objectToInsert) {
                // SELECT * FROM objectToInsert > annotation > person
                // ResultSet.getMetadata()
                return List.of("name", "age", "position");
            }

        });
    }
}
