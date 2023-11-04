package SassionFactory;

import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildsSessionFactory();

    private static SessionFactory buildsSessionFactory(){
        try{
//            new BootstrapServiceRegistryBuilder().build();
            return new Configuration().configure().buildSessionFactory();
        }catch (Exception ex){
            System.out.println("Execution " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
