package org.example.tennisv3.service.impl;
import org.example.tennisv3.model.Role;
import org.example.tennisv3.model.User;
import org.example.tennisv3.repository.RoleRepository;
import org.example.tennisv3.repository.UserRepository;
import org.example.tennisv3.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);


        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);


        user.getRoles().add(role);


        userRepository.save(user);
    }
}
