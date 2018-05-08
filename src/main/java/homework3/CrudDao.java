package homework3;

import org.hibernate.Session;


import java.util.ArrayList;
import java.util.List;

public class CrudDao<T> {
    private Class<T> tClass;


    public CrudDao(Class<T> tClass) {
        this.tClass = tClass;

    }

    public void create(T object) {
        Session session = this.openTransactSession();
        session.save(object);
        this.closeTransactSession(session);
    }

    public T getById(long id) {
        T result = null;
        Session session = this.openSession();
        result = session.get(tClass,  id);
        session.close();
        return result;
    }

    public void update(T object) {
        Session session = this.openTransactSession();
        session.update(object);
        this.closeTransactSession(session);
    }

    public void delete(T object) {
        Session session = this.openTransactSession();
        session.delete(object);
        this.closeTransactSession(session);
    }

    public List<T> listAll() {
        List<T> result = new ArrayList<>();

        String entityName = tClass.getSimpleName();
        String hql = "from " + entityName;

        Session session = this.openSession();
        result = session.createQuery(hql, tClass).list();
        session.close();

        return result;
    }

    public Session openSession() {
        return HibernateUtils.getInstance().createSession();
    }

    public Session openTransactSession() {
        Session session = this.openSession();
        session.beginTransaction();
        return session;
    }

    public void closeTransactSession(Session session) {
        session.getTransaction().commit();
        session.close();
    }
}