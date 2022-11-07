package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.User;
import models.Role;

/**
 * 
 * @author Daniel Porter
 */
public class UserDB {

    /**
     * get all users
     * @return users as a list
     * @throws Exception 
     */
    public List<User> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
            return users;
        } 
        finally {
            em.close();
        }
    }

    /**
     * Get user by email
     * @param email
     * @return User
     * @throws Exception 
     */
    public User get(String email) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            User user = em.find(User.class, email);
            return user;
        } finally {
            em.close();
        }
    }
    
    /**
     * Make new user
     * @param user
     * @throws Exception 
     */
    public void insert(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            
            Role role = user.getRole();
            role.getUserCollection().add(user);
            
            trans.begin();
            em.persist(user);
            em.merge(role);
            trans.commit();
            
        } catch (Exception ex) {
            trans.rollback();
        } 
        finally {
            em.close();
        }
    }

    /**
     * update an existing user
     * @param user
     * @throws Exception 
     */
    public void update(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            
            trans.begin();
            em.merge(user);
            trans.commit();
            
        } catch (Exception ex) {
            trans.rollback();
        } 
        finally {
            em.close();
        }
    }

    /**
     * delete a user by email
     * @param email
     * @throws Exception 
     */
    public void delete(String email) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try {
            
            User user = em.find(User.class, email);
            Role role = user.getRole();
            role.getUserCollection().remove(user);
            
            trans.begin();
            em.remove(em.merge(user));
            em.merge(role);
            trans.commit();
            
        } catch (Exception ex) {
            trans.rollback();
        } 
        finally {
            em.close();
        }
    }

}
