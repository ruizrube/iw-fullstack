package es.uca.iw.fullstackwebapp.user.controllers;

import es.uca.iw.fullstackwebapp.user.domain.User;
import es.uca.iw.fullstackwebapp.user.services.UserManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
public class UserRestController {

    private final UserManagementService service;

    public UserRestController(UserManagementService service) {
        this.service = service;
    }

    @GetMapping("/api/Users")
    public List<User> all() {
        return service.loadActiveUsers();
    }

    @PostMapping("/api/Users")
    void newUser(@RequestBody User newUser) {
        service.registerUser(newUser);

    }

    // Single item

    @GetMapping("/api/Users/{id}")
    User one(@PathVariable String id) {
        // TODO deal with invalid UUID
        return service.loadUserById(UUID.fromString(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found"));
    }

    @PutMapping("/api/Users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Integer id) {

        // TODO
        return newUser;
    }

    @DeleteMapping("/api/Users/{id}")
    void deleteUser(@PathVariable Integer id) {
        // TODO
    }
}
