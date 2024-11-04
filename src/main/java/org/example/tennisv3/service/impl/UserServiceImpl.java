package org.example.tennisv3.service.impl;
import jakarta.transaction.Transactional;
import org.example.tennisv3.dto.UserResponseDTO;
import org.example.tennisv3.model.User;
import org.example.tennisv3.repository.UserRepository;
import org.example.tennisv3.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
//    Implementation of assigning ADMIN/USER roles by loading authorities from User object
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getName());
        // Encode the user's password for security before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public UserResponseDTO updateUser(Long id, User updatedUser) {
        log.info("Updating user with id {}", id);

        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setUsername(updatedUser.getUsername());
            if (!updatedUser.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }
            user.setRoles(updatedUser.getRoles());
            User savedUser = userRepository.save(user);

            return new UserResponseDTO("User updated successfully", savedUser);
        }).orElseThrow(() -> {
            String errorMsg = "User with id " + id +" not found";
            log.error(errorMsg);
            return new UsernameNotFoundException(errorMsg);
        });
    }

    @Transactional
    public void deleteUserById(Long id) {
        log.info("Deleting user with id {}", id);
        userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return user;
        }).orElseThrow(() -> {
            String errorMsg2 = "User with id " + id + " not found";
            log.error(errorMsg2);
            return new UsernameNotFoundException(errorMsg2);
        });
    }



}
