package CourtBookingSystem.service;
import CourtBookingSystem.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {


    User saveUser(User user);
    List<User> getUsers();
    User updateUser(Long id, User updatedUser);
    void deleteUserById(Long id);
    Optional<User> getUserByUsername(String username);



}

