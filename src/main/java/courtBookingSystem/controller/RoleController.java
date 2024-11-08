package courtBookingSystem.controller;
import courtBookingSystem.dto.RoleToUserDTO;
import courtBookingSystem.model.Role;
import courtBookingSystem.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;



    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Role> getRoles() {
        return roleService.getRoles();
    }


    @PutMapping("/add-to-user")
    @ResponseStatus(HttpStatus.OK)
    public void addOrModifyRoleToUser(@RequestBody RoleToUserDTO roleToUserDTO) {
        roleService.addOrModifyRoleToUser(roleToUserDTO.getUsername(), roleToUserDTO.getRoleName());
    }


    @PostMapping("/createRole")
    @ResponseStatus(HttpStatus.CREATED)
    public void newRole(@RequestBody Role role) {
       roleService.newRole(role);
    }

//   This will NOT work if role which is meant to be deleted is assigned to any of the users because of the foreign key constraint.
//   To make it work use request removeRoleFromUser and then delete the role, reason why i did not wanted to implement
//   as one function is to keep additional layer of security so that Admin would not delete ROLE_ADMIN by mistake.
//    I will try to create mapping to drop everything that is related to specific user (userId)

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





