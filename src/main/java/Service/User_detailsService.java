package Service;

import Entity.User;
import Entity.User_details;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class User_detailsService {
    public void addUser_details(Session session, User_details userDetails,int id_user){
        session.beginTransaction();
        User user = session.get(User.class,id_user);
        userDetails.setUser_detail_id(user);
        session.persist(userDetails);
        session.getTransaction().commit();
    }

    public User_details takeUserDetailsByID(Session session,int id){
        session.beginTransaction();
        User_details userDetails ;
        userDetails = session.get(User_details.class,id);
        return userDetails;
    }
    public List<User_details> takeAllUserDetails(Session session){
        session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User_details> criteriaQuery = criteriaBuilder.createQuery(User_details.class);
        Root<User_details> userRoot = criteriaQuery.from(User_details.class);

        CriteriaQuery<User_details> all = criteriaQuery.select(userRoot);
        TypedQuery<User_details> allq = session.createQuery(all);

        List<User_details> userList = allq.getResultList();
        for(User_details user : userList){
            System.out.println(user);
        }
        return userList;
    }
    public void updateUserDetails(Session session,User_details userDetails){
        session.beginTransaction();
        session.merge(userDetails);
        session.getTransaction().commit();
    }
    public void removeUserDetails(Session session,int id_user){
        session.beginTransaction();
        User_details userDetails = session.get(User_details.class,id_user);
        session.remove(userDetails);
        session.getTransaction().commit();
    }
}
