package org.example.tennisv3.service;
import org.example.tennisv3.model.User;
import java.util.List;

public interface UserService {



    User saveUser(User user);
    List<User> getUsers();
    User updateUser(Long id, User updatedUser);
    void deleteUserById(Long id);

}

