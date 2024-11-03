package org.example.tennisv3.service;
import org.example.tennisv3.model.Role;


public interface RoleService {

    Role save(Role role);

    void addRoleToUser(String username, String roleName);


}
