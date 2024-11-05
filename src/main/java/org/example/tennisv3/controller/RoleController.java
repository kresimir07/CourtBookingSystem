package org.example.tennisv3.controller;
import org.example.tennisv3.dto.RoleToUserDTO;
import org.example.tennisv3.model.Role;
import org.example.tennisv3.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

//     Testirano i ispravno

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

// Testirano i ispravno mozda dodati samo log poruke

    @PutMapping("/add-to-user")
    @ResponseStatus(HttpStatus.OK)
    public void addOrModifyRoleToUser(@RequestBody RoleToUserDTO roleToUserDTO) {
        roleService.addOrModifyRoleToUser(roleToUserDTO.getUsername(), roleToUserDTO.getRoleName());
    }

//    Testirano i ispravno isto mozda dodati samo poruke, kao nova rola sacuvana

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void newRole(@RequestBody Role role) {
       roleService.newRole(role);
    }
// Testirano i ispravno - dodati log poruke
//   This will NOT work if role which is meant to be deleted is assigned to any of the users because of the foreign key constraint.
//   To make it work use method removeRoleFromUser and then delete the role, reason why i did not wanted to implement
//   as one function is to keep additional layer of security so that Admin would not delete ROLE_ADMIN by mistake
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoleById(@PathVariable Long id) {
        roleService.deleteRoleById(id);
    }


    @PutMapping("/remove-role-from-user")
    @ResponseStatus(HttpStatus.OK)
    public void removeRoleFromUser(@RequestBody RoleToUserDTO roleToUserDTO) {
        roleService.removeRoleFromUser(roleToUserDTO.getUsername(), roleToUserDTO.getRoleName());
    }


}





