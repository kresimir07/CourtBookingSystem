package org.example.tennisv3.service.impl;
import org.example.tennisv3.model.Role;
import org.example.tennisv3.model.User;
import org.example.tennisv3.repository.RoleRepository;
import org.example.tennisv3.repository.UserRepository;
import org.example.tennisv3.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public List<Role> getRoles() {
        log.info("Fetching all roles");
        return roleRepository.findAll();
    }

    @Override
    public void newRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        roleRepository.save(role);
    }


//    Method to replace existing and assign new role to User
    @Override
    public void addOrModifyRoleToUser(String username, String roleName) {
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

    @Transactional
    public void deleteRoleById(Long id) {
        log.info("Deleting role with id {}", id);
        roleRepository.findById(id).map(role -> {
            roleRepository.delete(role);
            return role;
        }).orElseThrow(() -> {
            String errorMsg2 = "Role with id " + id + " not found";
            log.error(errorMsg2);
            return new UsernameNotFoundException(errorMsg2);
        });
    }

    @Transactional
    public void removeRoleFromUser(String username, String roleName) {
        log.info("Removing role {} from user {}", roleName, username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        if (user != null && role != null) {
            user.getRoles().remove(role);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Role not found");
        }
    }

//    I haven't created method and route for modifying user role, because that change is possible from Users side



}
