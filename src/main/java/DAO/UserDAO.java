package DAO;

import Entity.User;
import org.hibernate.Session;

public interface UserDAO {
    public User takeUserById(Session session, int id);
    public void addUser(Session session,User user);
    public void updateUser(Session session,User user);
    public void removeUserById(Session session,int id);
    public void takeAllUser(Session session);
}
