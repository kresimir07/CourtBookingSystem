package org.example.tennisv3.controller;
import org.example.tennisv3.dto.RoleToUserDTO;
import org.example.tennisv3.model.Role;
import org.example.tennisv3.model.User;
import org.example.tennisv3.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
//    TODO
//ovo treba promijeniti u dodaj novu rolu
//    @GetMapping("/roles")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void saveRole(@RequestBody Role role) {
//        roleService.save(role);
//    }

    @PutMapping("/roles/add-to-user")
    @ResponseStatus(HttpStatus.OK)
    public void addRoleToUser(@RequestBody RoleToUserDTO roleToUserDTO) {
        roleService.addRoleToUser(roleToUserDTO.getUsername(), roleToUserDTO.getRoleName());
    }

    @GetMapping("/roles")
    @ResponseStatus(HttpStatus.OK)
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

}

//// https://github.com/Amazon-Java-0824/9-Demo-HelloHappySeeds/blob/master/src/main/java/com/scorsaro/hellowebagain/controller/SeedController.java
//Usporediti sa ovim i provjeriti zasto moj kod ne radi kad trebam promijenit varijablu


//2. "/api/roles/add-to-user" ne radi kako treba
//treba istraziti i vidjeti kako urediti rolu za usera
//
//Using Postman
//Set PATCH as the HTTP method.
//
//Enter the URL, e.g., http://localhost:8080/users/{id}/role.
//
//In the Body tab, use JSON to send the new role:
//
//json
//Copy code
//{
//        "name": "NewRole"
//        }
//
//
//        3. "/api/roles/remove-from-user
//        Ne radi, treba istraziti sto moram napisati u body da bi to radilo
//
//4./surface/surface-to-save- ne funkcionira kako treba isto treba biti POST
//
//5. /api/courts/courts-to-save mora biti kao POST
//
//@PostMapping("/courts")
//public ResponseEntity<Court> addCourt(@RequestBody Court court) {
//    Court createdCourt = courtService.saveCourt(court);
//    return ResponseEntity.status(HttpStatus.CREATED).body(createdCourt);
//}
//
//
//
//        In summary, PUT is used for creating or replacing resources,
//POST is used for creating or appending data to resources,
//and PATCH is used for partially updating existing resources.


