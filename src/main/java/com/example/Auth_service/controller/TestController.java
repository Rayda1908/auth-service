package com.example.Auth_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/user/profile")
    public ResponseEntity<String> getUserProfile(Authentication authentication) {
        return ResponseEntity.ok("Hello " + authentication.getName() + "! You have access to user endpoints.");
    }

    @GetMapping("/admin/dashboard")
    public ResponseEntity<String> getAdminDashboard(Authentication authentication) {
        return ResponseEntity.ok("Welcome Admin " + authentication.getName() + "! You have elevated access.");
    }
}