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
import org.junit.runners.Parameterized;

import java.util.List;


public class ProductServiceTest {
    static SessionFactory sessionFactory;
    static Session session;
    private static final Logger LOGGER = LogManager.getLogger(ProductServiceTest.class);
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
    public void testTakeProductById() {
        session.beginTransaction();
        Product user = session.get(Product.class, 1);
        session.getTransaction().commit();

        Assert.assertEquals(user.getProduct(),"milk");

    }
    @Test
    public void testUpdateProduct() {
        session.beginTransaction();
        Product user = session.get(Product.class, 6);
        String nameUser = "some shit";
        user.setProduct(nameUser);
        session.merge(user);
        Product user1 = session.get(Product.class, 6);
        session.getTransaction().commit();
        Assert.assertEquals(nameUser,user1.getProduct());
        session.beginTransaction();
        user.setProduct("bread");
        session.merge(user);
        session.getTransaction().commit();

    }



    @Test
    public void testTakeAllProduct() {
        session.beginTransaction();
        List<Integer> query = session.createQuery("select id from Product  ").getResultList();
        for(Integer id_user : query){
            System.out.println(id_user);
            Assert.assertEquals(id_user,(Integer) session.get(Product.class, id_user).getId());
        }
        session.getTransaction().commit();
    }
}