package homework5.service;

import homework3.CrudDao;
import homework3.HibernateUtils;
import homework3.entity.Company;
import homework3.entity.Customer;
import homework3.entity.Developer;
import homework3.entity.Project;

import java.util.HashMap;
import java.util.Map;

public class Service {
    private final Map<Class, CrudDao>  services = new HashMap<>();
    private final static Service INSTANCE = new Service();

    private Service() {

    }

    public void init() {
        this.services.put(Developer.class, new CrudDao<Developer>(Developer.class));
        this.services.put(Company.class, new CrudDao<Company>(Company.class));
        this.services.put(Project.class,new CrudDao<Project>(Project.class));
        this.services.put(Customer.class,new CrudDao<Customer>(Customer.class));
    }

    public static Service getInstance() {
        return INSTANCE;
    }

    public Object getAll(Class clazz) {
        return this.services.get(clazz).listAll();
    }

    public <T>  void   delete(Class clazz, T object) {
        this.services.get(clazz).delete(object);
    }

    public <T> void  create(Class clazz, T object) {
        this.services.get(clazz).create(object);
    }

    public <T> T getById(Class clazz, long id) {
        return (T) this.services.get(clazz).getById(id);
    }

    public <T> void  update(Class clazz, T object) {
        this.services.get(clazz).update(object);
    }


    public void close() {
        HibernateUtils.getInstance().close();
    }
}
