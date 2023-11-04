package Service;

import Entity.Order;
import Entity.User;
import org.hibernate.Session;

import java.util.List;

public class Order_Service {

    public void keepOrderByBuyer(Session session,int id_user){

        User user = session.get(User.class,id_user);
        Chopping_cart_Service choppingCartService = new Chopping_cart_Service();
        Order order = new Order();
        order.setId_user(user);
        order.setProduct(choppingCartService.readAllProductFromChoppingCart(session,id_user).toString());
        order.setPrice(choppingCartService.priceOfshoppingBasket());

        choppingCartService.removeAllProductFromChoppingCartForOrder(session,id_user);

        session.persist(order);
        session.getTransaction().commit();

    }
    public void readAllOrderByOneBuyer(Session session,int id_users){
        session.beginTransaction();
        User user = session.get(User.class,id_users);
        List<Order> listOrdersByOneUser = session.createQuery("select o from Order o where id_user = :id_buyer", Order.class).
                setParameter("id_buyer",user).
                getResultList();
        for(Order order : listOrdersByOneUser){
            System.out.println(order);
        }

    }
    public void readAllOrder(Session session){
        session.beginTransaction();
        List<Order> listOrder = session.createQuery("select p from Order p",Order.class).getResultList();
        for(Order order : listOrder){
            System.out.println(order);
        }
    }
}
