package ru.otus.homework10.database;

import ru.otus.homework10.model.UserDataSet;

public interface DBService {

    void save(UserDataSet uds);

    UserDataSet load(long id);
}
