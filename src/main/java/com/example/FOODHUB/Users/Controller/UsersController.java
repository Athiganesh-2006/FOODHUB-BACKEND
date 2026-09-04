package com.example.FOODHUB.Users.Controller;

import com.example.FOODHUB.Users.Entity.Users;
import com.example.FOODHUB.Users.Service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    // CREATE USER
    @PostMapping("/createuser")
    @ResponseStatus(HttpStatus.CREATED)
    public Users createUser(@RequestBody Users user) {
        return usersService.createUser(user);
    }

    // GET ALL USERS
    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    // GET USER BY ID
    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return usersService.getUserById(id);
    }

    // UPDATE USER
    @PutMapping("/{id}")
    public Users updateUser(
            @PathVariable Long id,
            @RequestBody Users user) {

        return usersService.updateUser(id, user);
    }

    // ACTIVATE USER
    @PatchMapping("/{id}/active")
    public Users activateUser(@PathVariable Long id) {
        return usersService.activateUser(id);
    }

    // DEACTIVATE USER
    @PatchMapping("/{id}/deactive")
    public Users deactivateUser(@PathVariable Long id) {
        return usersService.deactivateUser(id);
    }
}