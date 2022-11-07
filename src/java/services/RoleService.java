
package services;

import models.Role;
import dataaccess.RoleDB;
import java.util.ArrayList;
import java.util.List;

/**
 * Get roles from data access.
 * @author Long
 */
public class RoleService {
    private final RoleDB accessRoles = new RoleDB();
    
    
    public List<Role> getAll() throws Exception {
        List<Role> roles;
        
        roles = accessRoles.getAll();
        
        return roles;
    }
    
    
    public Role get(int roleID) throws Exception {
        Role role;
        
        role = accessRoles.get(roleID);
        
        return role;
    }
}
