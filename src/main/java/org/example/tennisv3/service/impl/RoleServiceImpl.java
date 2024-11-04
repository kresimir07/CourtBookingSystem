package org.example.tennisv3.service.impl;
import org.example.tennisv3.model.Role;
import org.example.tennisv3.model.User;
import org.example.tennisv3.repository.RoleRepository;
import org.example.tennisv3.repository.UserRepository;
import org.example.tennisv3.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }
//    Method to replace existing and assign new role to User
    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        User user = userRepository.findByUsername(username);
        Role newRole = roleRepository.findByName(roleName);
        if (user != null && newRole != null) {
            user.getRoles().clear();
            user.getRoles().add(newRole);
            userRepository.save(user);
        } else {
            throw new RuntimeException("User or role not found");
        }
    }

    @Override
    public List<Role> getRoles() {
        log.info("Fetching all roles");
        return roleRepository.findAll();
    }


}
