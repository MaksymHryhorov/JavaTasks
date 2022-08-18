package com.knubisoft.ORMv2;

import com.knubisoft.ORMv2.sourceInterf.DataReadWriteSource;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@RequiredArgsConstructor
@Getter
public class ConnectionReadWriteSource implements DataReadWriteSource {
    private final Connection source;
    private final String table;

    @Override
    @SneakyThrows
    public ResultSet getContent() {
        Statement statement = source.createStatement();
        return statement.executeQuery("SELECT * FROM " + table);
    }
}
