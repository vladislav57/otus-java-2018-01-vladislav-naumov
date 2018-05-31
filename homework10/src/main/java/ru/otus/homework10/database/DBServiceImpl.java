package ru.otus.homework10.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import ru.otus.homework10.model.UserDataSet;

import java.util.Set;
import java.util.function.Function;

public class DBServiceImpl implements DBService {

    private SessionFactory sessionFactory;

    public DBServiceImpl() {
        System.out.println("enter constructor");
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            System.out.println("start build session factory");
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
            System.out.println("Build session factory");
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if(sessionFactory != null)
            sessionFactory.close();
    }

    @Override
    public void save(UserDataSet uds) {
        runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            dao.save(uds);
            return true;
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
