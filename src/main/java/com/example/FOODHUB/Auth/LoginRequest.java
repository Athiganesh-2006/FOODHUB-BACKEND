package com.example.FOODHUB.Auth;

public record LoginRequest(
        String email,
        String password
) {
}