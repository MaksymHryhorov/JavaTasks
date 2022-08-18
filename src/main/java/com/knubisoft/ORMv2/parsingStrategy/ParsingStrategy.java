package com.knubisoft.ORMv2.parsingStrategy;

import com.knubisoft.ORMv2.sourceInterf.DataReadWriteSource;
import com.knubisoft.ORMv2.Table;

public interface ParsingStrategy<T extends DataReadWriteSource> {
    Table parseToTable(T content);
}