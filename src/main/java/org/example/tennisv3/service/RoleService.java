package org.example.tennisv3.service;
import org.example.tennisv3.model.Role;
import java.util.List;


public interface RoleService {

    void newRole(Role role);
    List<Role> getRoles();
    void addOrModifyRoleToUser(String username, String roleName);
    void deleteRoleById(Long id);
    void removeRoleFromUser(String username, String roleName);



}
