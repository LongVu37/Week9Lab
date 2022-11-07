
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import models.Role;

/**
 * 
 * @author Daniel Porter
 */
public class RoleDB {
    
    /**
     * Get All Roles
     * @return roles as a list
     * @throws Exception 
     */
    public List<Role> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            List<Role> roles = em.createNamedQuery("Role.findAll", Role.class).getResultList();
            return roles;
        } finally {
            em.close();
        }
    }
    
    /**
     * Get role based on id
     * @param roleId
     * @return Role
     * @throws Exception 
     */
    public Role get(int roleId) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Role role = em.find(Role.class, roleId);
            return role;
        } finally {
            em.close();
        }
    }
}
