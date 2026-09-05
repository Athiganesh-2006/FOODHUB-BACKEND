package com.example.FOODHUB.Auth;

public record RegisterResponse(
        Long id,
        String name,
        String email,
        String message
) {}

