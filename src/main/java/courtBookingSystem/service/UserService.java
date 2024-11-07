package courtBookingSystem.service;
import courtBookingSystem.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {


    void saveUser(User user);
    List<User> getUsers();
    User updateUser(Long id, User updatedUser);
    void deleteUserById(Long id);
    Optional<User> getUserByUsername(String username);



}

