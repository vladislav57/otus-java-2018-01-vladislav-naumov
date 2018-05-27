package ru.otus.homework09;

import java.sql.*;
import java.util.Properties;

public class Executor {

    private Connection connection;

    public <T> T execQuery(String query, TResultHandler<T> handler) throws SQLException, IllegalAccessException, NoSuchFieldException {
        Statement stmt = connection.createStatement();
        stmt.execute(query);
        ResultSet result = stmt.getResultSet();
        T value = handler.handle(result);
        result.close();
        stmt.close();
        return value;
    }

    public int execUpdate(String update) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(update);
            return stmt.getUpdateCount();
        }
    }

    public Executor(Properties props) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
        this.connection = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"), props.getProperty("password"));
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        connection.close();
    }
}
