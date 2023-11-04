package Service;

import Entity.User;
import jakarta.persistence.QueryHint;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.annotations.QueryHints;

import java.util.List;
import java.util.Map;

public class UserService {
    public void nMinusOne(Session session) {
        session.beginTransaction();
        List<User> user =  session.createQuery
                        ("SELECT p from User p right join fetch p.userDetails_join",User.class).
                getResultList();
        for(User users : user){
            if(users.getUserDetails_join().getAge() < 25) {
                System.out.println("go away");
            }
        }
    }

    public User takeUserById(Session session,int id){
        User user = new User();

        try {
            session.beginTransaction();
            user = session.get(User.class, id);
        }catch (Exception ex){
            session.getTransaction().rollback();
            ex.printStackTrace();
        }
        return user;
    }
    public void addUser(Session session,User user){
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
    }
    public void updateUser(Session session,User user){
        session.beginTransaction();
        session.merge(user);
        session.getTransaction().commit();
    }
    public void removeUserById(Session session,int id){
        User user = new User();
        user.setId(id);

        session.beginTransaction();
        session.remove(user);
        session.getTransaction().commit();
    }
    public void takeAllUser(Session session){
        session.getTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);

        CriteriaQuery<User> all = criteriaQuery.select(userRoot);
        TypedQuery<User> allq = session.createQuery(all);

        List<User> userList = allq.getResultList();
        for(User user : userList){
            System.out.println(user);
        }



    }
}
