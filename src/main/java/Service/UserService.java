package Service;

import DAO.UserDAO;
import Entity.User;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;


import java.util.List;


public class UserService implements UserDAO {
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    public void nMinusOne(Session session) {
        session.beginTransaction();
        LOGGER.info("Successful begin transaction");
        List<User> user =  session.createQuery
                        ("SELECT p from User p right join fetch p.userDetails_join",User.class).
                getResultList();
        LOGGER.info("User was select from dateBase");
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
            LOGGER.info("Successful begin transaction");
            user = session.get(User.class, id);
            LOGGER.info("User was get from DB");
        }catch (Exception ex){
            LOGGER.error("User was not get from DB");
            session.getTransaction().rollback();
            ex.printStackTrace();
        }

        return user;
    }
    public void addUser(Session session,User user){
        session.beginTransaction();
        LOGGER.info("Successful begin transaction");
        session.persist(user);
        LOGGER.info("User was successful create and add to DB");
        session.getTransaction().commit();
        LOGGER.info("Commit successful");
    }
    public void updateUser(Session session,User user){
        session.beginTransaction();
        LOGGER.info("Successful begin transaction");
        session.merge(user);
        LOGGER.info("User was successful update");
        session.getTransaction().commit();
        LOGGER.info("Commit successful");
    }
    public void removeUserById(Session session,int id){
        User user = new User();
        user.setId(id);

        session.beginTransaction();
        LOGGER.info("Successful begin transaction");
        session.remove(user);
        LOGGER.info("User was successful remove from DB");
        session.getTransaction().commit();
        LOGGER.info("Commit successful");
    }
    public void takeAllUser(Session session){
        session.getTransaction();
        LOGGER.info("Successful begin transaction");
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
