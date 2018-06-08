package homework10.database;

import homework10.model.UserDataSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.function.Function;

public class DBServiceImpl implements DBService {

    private SessionFactory sessionFactory;

    public DBServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if(sessionFactory != null)
            sessionFactory.close();
    }

    @Override
    public long save(UserDataSet uds) {
        return runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            dao.save(uds);
            return uds.getUserId();
        });
    }

    @Override
    public UserDataSet load(long id) {
        return runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            return dao.load(id);
        });
    }


    private <R> R runInSession(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }

}
