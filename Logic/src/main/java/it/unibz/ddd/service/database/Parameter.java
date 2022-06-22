package it.unibz.ddd.service.database;

import java.sql.Types;

public class Parameter<T> {
    private final T content;

    public Parameter(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public int getSqlDataType() {
        if (content instanceof String)
            return Types.VARCHAR;
        else
            return Types.INTEGER;
    }
}
