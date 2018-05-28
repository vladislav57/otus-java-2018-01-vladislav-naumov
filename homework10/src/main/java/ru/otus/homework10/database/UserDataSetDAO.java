package ru.otus.homework10.database;

import org.hibernate.Session;
import ru.otus.homework10.model.UserDataSet;

public class UserDataSetDAO {

    private Session session;

    public UserDataSetDAO(Session session) {
        this.session = session;
    }

    public void save(UserDataSet uds) {
        session.save(uds);
    }

    public UserDataSet load(long id) {
        return new UserDataSet(session.load(UserDataSet.class, id));
    }
}
