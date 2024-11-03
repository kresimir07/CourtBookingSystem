package org.example.tennisv3.demo;

import org.example.tennisv3.model.Court;
import org.example.tennisv3.model.Role;
import org.example.tennisv3.model.Surface;
import org.example.tennisv3.model.User;
import org.example.tennisv3.service.CourtService;
import org.example.tennisv3.service.RoleService;
import org.example.tennisv3.service.SurfaceService;
import org.example.tennisv3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.example.tennisv3.service.impl.SurfaceServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;
    private final SurfaceService surfaceService;
    private final CourtService courtService;

    @Override
    public void run(String... args) throws Exception {
        roleService.save(new Role("ROLE_USER"));
        roleService.save(new Role("ROLE_ADMIN"));


        userService.saveUser(new User("Mario Maric", "mmaric", "1234"));
        userService.saveUser(new User("Kruno Vulic", "kvulic", "1234"));
        userService.saveUser(new User("Valentina Vulic", "vvulic", "1234"));
        userService.saveUser(new User("Hana Colic", "hcolic", "1234"));

        roleService.addRoleToUser("mmaric", "ROLE_USER");
        roleService.addRoleToUser("kvulic", "ROLE_ADMIN");
        roleService.addRoleToUser("vvulic", "ROLE_USER");
        roleService.addRoleToUser("hcolic", "ROLE_ADMIN");

        surfaceService.saveSurfaceType(new Surface("Clay"));
        surfaceService.saveSurfaceType(new Surface("Grass"));
        surfaceService.saveSurfaceType(new Surface("Hard"));
        surfaceService.saveSurfaceType(new Surface("Carpet"));
//        TODO ADD some sample court data to DataLoader
//        courtService.saveCourtName(new Court("Court A","Clay", "False"));









    }
}
