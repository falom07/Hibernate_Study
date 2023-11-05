package Service;


import Entity.User;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.hibernate.cfg.Configuration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.runners.Parameterized;

import java.util.List;



public class UserServiceTest {
    static SessionFactory sessionFactory;
    static Session session;
    private static final Logger LOGGER = LogManager.getLogger(UserServiceTest.class);
    static int idUser;
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
    public void testTakeUserById() {
        session.beginTransaction();
        User user = session.get(User.class, 1);
        session.getTransaction().commit();

        Assert.assertEquals(user.getName(),"Avada");

    }

    @Test
    public void testAddUser() {
        session.beginTransaction();
        User user = new User("Lora2288*35");
        session.persist(user);
        User user1 = session.get(User.class,user.getId());
        session.getTransaction().commit();
        idUser = user.getId();
        Assert.assertEquals(user,user1);

    }
    @Test
    public void testUpdateUserDetails() {
        session.beginTransaction();
        User user = session.get(User.class,idUser);
        String nameUser = "Bob236*3";
        user.setName(nameUser);
        session.merge(user);
        User user1 = session.get(User.class,idUser);
        session.getTransaction().commit();
        Assert.assertEquals(nameUser,user1.getName());

    }

//    @Test(expected = IllegalArgumentException.class)
    @Test
    public void testRemoveUserDetailsById() {

        User user = session.get(User.class,idUser);
        session.remove(user);
        User user2 = session.get(User.class,idUser);
        session.getTransaction().commit();
        Assert.assertNull(user2);

    }

    @Test
    public void testTakeAllUserDetails() {
        session.beginTransaction();
        List<Integer> query = session.createQuery("select id from User  ").getResultList();
        for(Integer id_user : query){
            System.out.println(id_user);
            Assert.assertEquals(id_user,(Integer) session.get(User.class, id_user).getId());
        }
    }
}