package com.knubisoft.ORMv2;

import com.knubisoft.ORMv2.sourceInterf.DataReadWriteSource;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;

@RequiredArgsConstructor
@Getter
public class ConnectionReadWriteSource implements DataReadWriteSource {
    private final Connection source;

    @Override
    @SneakyThrows
    public Connection getContent() {
        return source;
    }
}
