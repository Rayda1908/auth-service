package com.example.Auth_service.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    // Public endpoint: anyone can view this without logging in
    @GetMapping("/")
    public String home() {
        return "Welcome to the Public Home Page!";
    }

    // Accessible by users with ROLE_USER or ROLE_ADMIN
    @GetMapping("/user/profile")
    public String userProfile(Authentication authentication) {
        return "Hello " + authentication.getName() + "! You have access to user endpoints.";
    }

    // Accessible ONLY by users with ROLE_ADMIN
    @GetMapping("/admin/dashboard")
    public String adminDashboard(Authentication authentication) {
        return "Welcome Admin " + authentication.getName() + "! You have full administrative access.";
    }
}