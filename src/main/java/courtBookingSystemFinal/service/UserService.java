package courtBookingSystemFinal.service;
import courtBookingSystemFinal.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {


    void createNewUser(User user);
    List<User> getUsers();
    User updateUser(Long id, User updatedUser);
    void deleteUserById(Long id);
    Optional<User> getUserByUsername(String username);



}

