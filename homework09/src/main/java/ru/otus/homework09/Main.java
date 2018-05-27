package ru.otus.homework09;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Properties props = getPropertiesFromFile();
        UserDataSetDbHelper userDataSetDbHelper = new UserDataSetDbHelper(new Executor(props));
        userDataSetDbHelper.createTable();
        UserDataSet udsSave = new UserDataSet(1, "testname", 33);
        userDataSetDbHelper.save(udsSave);
        UserDataSet udsLoad = userDataSetDbHelper.load(1L, UserDataSet.class);
        //userDataSetDbHelper.printAll(UserDataSet.class);
        System.out.println(udsSave);
        System.out.println(udsLoad);
    }

    public static Properties getPropertiesFromFile() throws IOException {
        Properties props = new Properties();
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");
        props.load(in);
        return props;
    }
}
