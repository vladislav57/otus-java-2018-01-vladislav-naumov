package ru.otus.homework09;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.sql.SQLException;

public class UserDataSetDbHelper {

    private Executor executor;

    public UserDataSetDbHelper(Executor executor) {
        this.executor = executor;
    }

    public void createTable() throws SQLException {
        executor.execUpdate("CREATE TABLE MYDATA (ID BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), NAME VARCHAR(255), AGE INT )");
    }

    public <T extends DataSet> void save(T uds) throws SQLException, IllegalAccessException {
        Field[] fields = uds.getClass().getDeclaredFields();
        String[] intoArr = new String[fields.length];
        String[] valuesArr = new String[fields.length];
        for(int i=0; i<fields.length; i++) {
            fields[i].setAccessible(true);
            if(fields[i].getType().equals(String.class)) {
                valuesArr[i] = cover(fields[i].get(uds).toString());
            } else {
                valuesArr[i] = fields[i].get(uds).toString();
            }
            intoArr[i] = fields[i].getName();
        }
        String into   = StringUtils.join(intoArr, ", ");
        String values = StringUtils.join(valuesArr, ", ");
        String statement = "INSERT INTO MYDATA (" + into + ") VALUES (" + values + ")";
        //System.out.println(statement);
        executor.execUpdate(statement);
    }

    public String cover(String s) {
        return "'" + s + "'";
    }

    public <T extends DataSet> T load(long id, Class<T> tClass) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        T elem = tClass.newInstance();
        for(Field field : elem.getClass().getDeclaredFields()) {
            field.setAccessible(true);
        }
        elem.setId(id);

        executor.execQuery("SELECT * FROM MYDATA WHERE ID = " + id + "", result -> {
            result.next();
            for(Field field : elem.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                field.set(elem , result.getObject(field.getName()));
            }
            return elem;
        });

        return elem;
    }

    public <T extends DataSet> void printAll(Class<T> tClass) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        T elem = tClass.newInstance();
        for(Field field : elem.getClass().getDeclaredFields()) {
            field.setAccessible(true);
        }

        executor.execQuery("SELECT * FROM MYDATA", result -> {
            while(result.next()) {
                for (Field field : elem.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    field.set(elem, result.getObject(field.getName()));
                }
                elem.setId(result.getLong("id"));
                System.out.println(elem);
            }
            return elem;
        });

    }
}
