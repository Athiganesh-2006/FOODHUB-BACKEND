//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.FOODHUB.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/users"})
public class UsersController {
    @Autowired
    private final com.example.FOODHUB.Users.UsersService usersService;

   public UsersController(com.example.FOODHUB.Users.UsersService usersService) {
       this.usersService = usersService;
   }

    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return usersService.createUser(user);
    }

    @PutMapping({"/{id}"})
    public Users updateUser(@PathVariable Long id, @RequestBody Users user) {
        return usersService.updateUser(id, user);
    }

    @GetMapping("/")
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }
}
