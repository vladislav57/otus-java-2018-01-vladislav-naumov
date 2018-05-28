package ru.otus.homework10;

import ru.otus.homework10.database.DBService;
import ru.otus.homework10.database.DBServiceImpl;
import ru.otus.homework10.model.UserDataSet;

public class Main {
    public static void main(String[] args) {
        DBService service = new DBServiceImpl();
        UserDataSet uds = new UserDataSet(1L, "testname", 22);
        service.save(uds);
        uds = service.load(1L);
        System.out.println(uds);
        System.out.println("end");
    }
}
