package homework10.database;

import homework10.model.UserDataSet;
import org.hibernate.Session;

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
