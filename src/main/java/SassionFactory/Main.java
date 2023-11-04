package SassionFactory;

import Entity.Product;
import Entity.User;
import Entity.User_details;
import Service.*;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        Session session =  HibernateUtil.getSessionFactory().openSession();

        Order_Service orderService = new Order_Service();
        orderService.readAllOrder(session);

        session.close();
    }
}

