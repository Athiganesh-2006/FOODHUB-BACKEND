package com.example.FOODHUB.Auth;

public record LoginResponse(
        Long id,
        String name,
        String email,
        String role,
        String message
) {
}