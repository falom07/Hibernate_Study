package DAO;

import Entity.Product;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public interface ProductDAO {
    public void addProduct(Session session, Product product);
    public Product takeProductByID(Session session,int id);
    public List<Product> takeAllProduct(Session session);
    public void updateProduct(Session session,Product product);
    public void removeProduct(Session session,int id_product);
}
