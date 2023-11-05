package DAO;

import Entity.Product;
import Entity.User;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public interface ChoppingCartDAO {
    public void addProductFromChoppingCart(Session session, int id_product, int id_user);
    public void removeProductFromChoppingCart(Session session,int id_product,int id_user);
    public void removeAllProductFromChoppingCart(Session session,int id_buyer);
    public void removeAllProductFromChoppingCartForOrder(Session session,int id_buyer);
    public StringBuilder readAllProductFromChoppingCart(Session session, int id_buyer);
}
