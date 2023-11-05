package Service;


import Entity.Product;
import Entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;


public class ChoppingCartServiceTest {
    static SessionFactory sessionFactory;
    static Session session;
    private static final Logger LOGGER = LogManager.getLogger(ChoppingCartServiceTest.class);
    static int idProduct;
    @BeforeClass
    public static void createEntityManegerAndOpenSession(){
        sessionFactory = new Configuration().configure("hibernateTest.cfg.xml").buildSessionFactory();
        session = sessionFactory.openSession();
        LOGGER.info("SessionFactory create and session open successful");
    }
    @AfterClass
    public static void closeSession(){
        session.close();
        sessionFactory.close();
        LOGGER.info("Session and SessionFactory close successful");
    }

    @Test
    public void addProductFromChoppingCart(){
        session.beginTransaction();
        User user = session.get(User.class,1);
        Product product = session.get(Product.class,1);
        int size = user.getProductList().size();
        user.getProductList().add(product);

        session.persist(user);
        int size2 = user.getProductList().size();
        session.getTransaction().commit();
        Assert.assertEquals(size,size2-1);
    }

    @Test
    public void removeProductFromChoppingCart(){
        session.beginTransaction();
        User user = session.get(User.class,1);
        Product product = session.get(Product.class,1);
        user.getProductList().add(product);
        int size2 = user.getProductList().size();
        user.getProductList().remove(product);

        session.getTransaction().commit();
        Assert.assertEquals(size2,user.getProductList().size()+1);
    }



}
