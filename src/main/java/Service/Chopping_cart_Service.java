package Service;

import Entity.Product;
import Entity.User;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class Chopping_cart_Service {

    private static int price;
    public void addProductFromChoppingCart(Session session,int id_product,int id_user){
        session.beginTransaction();
        User user = session.get(User.class,id_user);
        Product product = session.get(Product.class,id_product);
        user.getProductList().add(product);

        session.persist(user);
        session.getTransaction().commit();
    }
    public void removeProductFromChoppingCart(Session session,int id_product,int id_user){
        session.beginTransaction();
        User user = session.get(User.class,id_user);
        Product product = session.get(Product.class,id_product);

        user.getProductList().remove(product);
        session.getTransaction().commit();
    }
    public void removeAllProductFromChoppingCart(Session session,int id_buyer){
        session.beginTransaction();
        User user = session.get(User.class,id_buyer);
        user.getProductList().clear();
        session.getTransaction().commit();
    }
    public void removeAllProductFromChoppingCartForOrder(Session session,int id_buyer){

        User user = session.get(User.class,id_buyer);
        user.getProductList().clear();

    }
    public StringBuilder readAllProductFromChoppingCart(Session session, int id_buyer){
        session.beginTransaction();

        User user = session.get(User.class,id_buyer);
        List<Product> productListFromChopingCart= new ArrayList<>(user.getProductList());
        StringBuilder listProducts = new StringBuilder();
        for(Product product : productListFromChopingCart){
            listProducts.append(product.getProduct()).append(",");
            price += product.getPrice();
        }
        return  listProducts.delete(listProducts.length()-1,listProducts.length());
    }
    public int priceOfshoppingBasket(){
        return price;
    }

}
