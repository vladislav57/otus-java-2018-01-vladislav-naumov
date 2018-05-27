package ru.otus.homework09;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface TResultHandler<T> {
    T handle(ResultSet result) throws SQLException, IllegalAccessException, NoSuchFieldException;
}
