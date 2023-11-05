package SassionFactory;

import Entity.Product;
import Entity.User;
import Entity.User_details;
import Service.*;
import org.hibernate.Session;

import org.apache.logging.log4j.*;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        LOGGER.info("Hi");
        LOGGER.error("Hi");
        Session session =  HibernateUtil.getSessionFactory().openSession();
        LOGGER.info("Successful create Session ");
        UserService userService = new UserService();
        User user = new User("Vana");
        userService.addUser(session,user);

        session.close();
        LOGGER.info("Close Session successful");
    }
}

