package org.example.tennisv3.service;
import org.example.tennisv3.model.Role;

import java.util.List;


public interface RoleService {

    Role save(Role role);

    List<Role> getRoles();

    void addRoleToUser(String username, String roleName);


}
