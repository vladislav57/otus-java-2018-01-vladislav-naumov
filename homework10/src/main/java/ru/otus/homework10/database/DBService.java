package ru.otus.homework10.database;

import ru.otus.homework10.model.UserDataSet;

import java.util.Set;

public interface DBService {

    void save(UserDataSet uds);

    UserDataSet load(long id);

}
