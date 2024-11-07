package courtBookingSystem.demo;
import courtBookingSystem.service.*;
import lombok.Data;
import courtBookingSystem.model.Court;
import courtBookingSystem.model.Role;
import courtBookingSystem.model.Surface;
import courtBookingSystem.model.User;
//import org.example.CourtBookingSystem.service.*;
//org.example.
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;

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


        userService.saveUser(new User("Mario Maric", "mmaric", "1234"));
        userService.saveUser(new User("Kruno Vulic", "kvulic", "1234"));
        userService.saveUser(new User("Valentina Vulic", "vvulic", "1234"));
        userService.saveUser(new User("Hana Colic", "hcolic", "1234"));

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

        // Save bookings
        userService.getUserByUsername("mmaric")
                .ifPresent(user -> bookingService.newBookingRequest(
                        user.getId(),
                        court1.getId(),
                        LocalDateTime.of(2024, 12, 1, 10, 0)
                ));

        userService.getUserByUsername("kvulic")
                .ifPresent(user -> bookingService.newBookingRequest(
                        user.getId(),
                        court2.getId(),
                        LocalDateTime.of(2024, 12, 1, 10, 0)
                ));

        userService.getUserByUsername("vvulic")
                .ifPresent(user -> bookingService.newBookingRequest(
                        user.getId(),
                        court3.getId(),
                        LocalDateTime.of(2024, 12, 1, 10, 0)
                ));

        userService.getUserByUsername("hcolic")
                .ifPresent(user -> bookingService.newBookingRequest(
                        user.getId(),
                        court4.getId(),
                        LocalDateTime.of(2024, 12, 1, 10, 0)
                ));









    }



}

