package SassionFactory;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
    private static final Logger LOGGER = LogManager.getLogger(HibernateUtil.class);
    private static final SessionFactory sessionFactory = buildsSessionFactory();

    private static SessionFactory buildsSessionFactory(){
        try{
            LOGGER.warn("Create SessionManager");
//            new BootstrapServiceRegistryBuilder().build();
            return new Configuration().configure().buildSessionFactory();
        }catch (Exception ex){
            LOGGER.error("SessionManeger did not create exeption -> {}",ex);
            System.out.println("Execution " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
