package homework3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private SessionFactory sessionFactory;

    private static HibernateUtils instance = new HibernateUtils();

    private HibernateUtils() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static HibernateUtils getInstance() {
        return instance;
    }

    public Session createSession() {
        return sessionFactory.openSession();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void close() {
        sessionFactory.close();
    }
}
