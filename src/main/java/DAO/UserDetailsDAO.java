package DAO;

import Entity.User_details;
import org.hibernate.Session;

import java.util.List;

public interface UserDetailsDAO {
    public void addUser_details(Session session, User_details userDetails, int id_user);
    public User_details takeUserDetailsByID(Session session,int id);
    public List<User_details> takeAllUserDetails(Session session);
    public void updateUserDetails(Session session,User_details userDetails);
    public void removeUserDetails(Session session,int id_user);
}
