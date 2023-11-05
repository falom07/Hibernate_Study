package Service;


import Entity.User;
import Entity.User_details;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.*;
import org.junit.runners.Parameterized;

import java.util.List;


public class UserDetailsTest {
    static SessionFactory sessionFactory;
    static Session session;
    private static final Logger LOGGER = LogManager.getLogger(UserDetailsTest.class);

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
    public void testTakeUserDetailsById() {
        session.beginTransaction();
        User_details user = session.get(User_details.class, 1);
        session.getTransaction().commit();

        Assert.assertEquals(user.getPhone(),"12313131");

    }

    @Test
    public void testAddUserDetails() {
        session.beginTransaction();
        User user = new User("vasa");
        User_details userDetails = new User_details((byte) 12,"Ponomarova","2324242");

        userDetails.setUser_detail_id(user);
        session.persist(userDetails);

        User_details userDetails1 = session.get(User_details.class,user.getId());
        session.getTransaction().commit();
        session.beginTransaction();
        Assert.assertEquals(userDetails,userDetails1);
        session.remove(userDetails);
        session.getTransaction().commit();

    }
    @Test
    public void testUpdateUserDetails() {
        session.beginTransaction();
        User_details userDetails = session.get(User_details.class,2);
        String phone = "4356436745";
        userDetails.setPhone("4356436745");
        session.merge(userDetails);
        User_details Userd = session.get(User_details.class,2);
        session.getTransaction().commit();
        Assert.assertEquals(phone,userDetails.getPhone());
        session.beginTransaction();
        userDetails.setPhone("435642342745");
        session.merge(userDetails);
        session.getTransaction().commit();

    }

//    @Test(expected = IllegalArgumentException.class)
    @Test
    public void testRemoveUserDetailsById() {
        session.beginTransaction();

        User user = new User("vasa");
        User_details userDetails = new User_details((byte) 12,"Ponomarova","2324242");

        userDetails.setUser_detail_id(user);
        session.persist(userDetails);
        int user_id = user.getId();
        User_details userDetails1 = session.get(User_details.class,user.getId());
        session.getTransaction().commit();
        session.beginTransaction();
        session.remove(userDetails);
        User_details user_details = session.get(User_details.class,user_id);
        session.getTransaction().commit();
        Assert.assertNull(user_details);

    }

    @Test
    public void testTakeAllUser() {
        session.beginTransaction();
        List<String> query = session.createQuery("select phone from User_details  ").getResultList();
        for(String id_user : query){
            System.out.println(id_user);
            Assert.assertEquals(id_user,session.get(User_details.class, 1).getPhone());
            break;
        }
        session.getTransaction().commit();
    }
}