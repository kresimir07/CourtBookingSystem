package courtBookingSystem.demo;
import courtBookingSystem.model.*;
import courtBookingSystem.service.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Optional;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Data
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final SurfaceService surfaceService;
    private final CourtService courtService;
    private final BookingService bookingService;

    @Override
    public void run(String... args) {
        roleService.newRole(new Role("ROLE_USER"));
        roleService.newRole(new Role("ROLE_ADMIN"));


        userService.createNewUser(new User("Mario Maric", "mmaric", "1234"));
        userService.createNewUser(new User("Kruno Vulic", "kvulic", "1234"));
        userService.createNewUser(new User("Valentina Vulic", "vvulic", "1234"));
        userService.createNewUser(new User("Hana Colic", "hcolic", "1234"));

        roleService.addOrModifyRoleToUser("mmaric", "ROLE_USER");
        roleService.addOrModifyRoleToUser("kvulic", "ROLE_ADMIN");
        roleService.addOrModifyRoleToUser("vvulic", "ROLE_USER");
        roleService.addOrModifyRoleToUser("hcolic", "ROLE_ADMIN");

        // Save surface - had to declare variables first
        Surface clay = surfaceService.newSurface(new Surface("Clay"));
        Surface grass = surfaceService.newSurface(new Surface("Grass"));
        Surface hard = surfaceService.newSurface(new Surface("Hard"));
        Surface carpet = surfaceService.newSurface(new Surface("Carpet"));


        // Save courts - able to save with above surface variables
        Court court1 = courtService.newCourt(new Court("Philippe Chatrier Court", clay, FALSE));
        Court court2 = courtService.newCourt(new Court("Palexpo", carpet, FALSE));
        Court court3 = courtService.newCourt(new Court("Centre Court", grass, FALSE));
        Court court4 = courtService.newCourt(new Court("Rod Laver Arena", hard, TRUE));


        // Create sample bookings - used different durations of booking to test functionality for math task
        createSampleBooking("mmaric", court1, LocalDateTime.of(2024, 12, 1, 10, 0),
                LocalDateTime.of(2024, 12, 1, 11, 30)); // 1.5-hour booking

        createSampleBooking("kvulic", court2, LocalDateTime.of(2024, 12, 2, 9, 30),
                LocalDateTime.of(2024, 12, 2, 11, 30)); // 2-hour booking

        createSampleBooking("vvulic", court3, LocalDateTime.of(2024, 12, 3, 14, 0),
                LocalDateTime.of(2024, 12, 3, 14, 30)); // 30 minutes booking

        createSampleBooking("hcolic", court4, LocalDateTime.of(2024, 12, 4, 16, 0),
                LocalDateTime.of(2024, 12, 4, 18, 30)); // 2.5-hour booking
    }

    private void createSampleBooking(String username, Court court, LocalDateTime startTime, LocalDateTime endTime) {
        Optional<User> userOptional = userService.getUserByUsername(username);
        userOptional.ifPresent(user -> bookingService.newBookingRequest(
                user.getId(),
                court.getId(),
                startTime,
                endTime
        ));
    }

}

