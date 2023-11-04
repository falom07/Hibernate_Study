package Service;

import Entity.Product;
import Entity.User;
import Entity.User_details;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class Product_Service {
    public void addProduct(Session session, Product product){
        session.beginTransaction();
        session.persist(product);
        session.getTransaction().commit();
    }

    public Product takeProductByID(Session session,int id){
        session.beginTransaction();
        Product product ;
        product = session.get(Product.class,id);

        return product;
    }
    public List<Product> takeAllProduct(Session session){
        session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);

        CriteriaQuery<Product> all = criteriaQuery.select(productRoot);
        TypedQuery<Product> allq = session.createQuery(all);

        List<Product> productList = allq.getResultList();
        for(Product product : productList){
            System.out.println(product);
        }
        return productList;
    }
    public void updateProduct(Session session,Product product){
        session.beginTransaction();
        session.merge(product);
        session.getTransaction().commit();
    }
    public void removeProduct(Session session,int id_product){
        session.beginTransaction();
        Product product = session.get(Product.class,id_product);
        session.remove(product);
        session.getTransaction().commit();
    }
}
