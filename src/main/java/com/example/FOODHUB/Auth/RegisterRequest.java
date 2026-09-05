package com.example.FOODHUB.Auth;

public record RegisterRequest(
        String name,
        String email,
        String password,
        String phone,
        String company
) {
}