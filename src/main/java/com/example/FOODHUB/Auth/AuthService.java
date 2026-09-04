package com.example.FOODHUB.Auth;

import com.example.FOODHUB.Users.Entity.Role;
import com.example.FOODHUB.Users.Entity.Users;
import com.example.FOODHUB.Users.Repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse register(RegisterRequest request) {

        if (usersRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Users user = new Users();

        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setPhone(request.phone());
        user.setCompany(request.company());
        user.setRole(Role.CUSTOMER);

        Users savedUser = usersRepository.save(user);

        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                "Registration Successful"
        );
    }

    public LoginResponse login(LoginRequest request) {

        Users user = usersRepository.findByEmail(request.email())
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid email or password"));

        if (!passwordEncoder.matches(
                request.password(),
                user.getPassword())) {

            throw new IllegalArgumentException(
                    "Invalid email or password");
        }

        return new LoginResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().name(),
                "Login Successful"
        );
    }
}