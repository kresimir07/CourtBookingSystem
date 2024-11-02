package org.example.tennisv3.service;
import org.example.tennisv3.model.User;
import java.util.List;

public interface UserService {

    User saveUser(User user);
    User getUser(String username);
    List<User> getUsers();
}

