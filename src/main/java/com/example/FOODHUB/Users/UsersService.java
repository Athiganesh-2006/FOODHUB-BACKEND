package com.example.FOODHUB.Users;

import com.example.FOODHUB.Users.Users;
import com.example.FOODHUB.Users.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private final UsersRepository userRepository;

    public UsersService(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create User
    public Users createUser(Users user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        user.setActive(true);

        return userRepository.save(user);
    }

    // Get User by ID
    public Users getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    // Get User by Email
    public Users getUserByEmail(String email) {

        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Get All Users
    public List<Users> getAllUsers() {

        return userRepository.findAll();
    }

    public Users updateUser(Long id, Users updatedUser) {

        Users existingUser = getUserById(id);

        existingUser.setName(updatedUser.getName());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setCompany(updatedUser.getCompany());

        return userRepository.save(existingUser);
    }

    // Activate User
    public Users activateUser(Long id) {

        Users user = getUserById(id);

        user.setActive(true);

        return userRepository.save(user);
    }

    // Deactivate User
    public Users deactivateUser(Long id) {

        Users user = getUserById(id);

        user.setActive(false);

        return userRepository.save(user);
    }
}
