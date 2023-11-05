package DAO;

import Entity.Order;
import Entity.User;
import Service.Chopping_cart_Service;
import org.hibernate.Session;

import java.util.List;

public interface OrderDAO {
    public void keepOrderByBuyer(Session session, int id_user);
    public void readAllOrderByOneBuyer(Session session,int id_users);
    public void readAllOrder(Session session);
}
