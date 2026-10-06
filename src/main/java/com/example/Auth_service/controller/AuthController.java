package com.example.Auth_service.controller;

import com.example.Auth_service.dto.RegisterRequest;
import com.example.Auth_service.service.CustomUserDetailsService;
import org.springframework.http.ResponseEntity; //// Returns an HTTP 200 with the success message. If the username is already taken, it catches the exception and returns an HTTP 400 Bad Request.
import org.springframework.web.bind.annotation.PostMapping; // Combines with the class route to make the full URL
import org.springframework.web.bind.annotation.RequestBody; // Takes the incoming JSON body (username and password) and places it into our RegisterRequest DTO object.
import org.springframework.web.bind.annotation.RequestMapping; // Every route in this class begins with /auth.
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CustomUserDetailsService userDetailsService;

    public AuthController(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        try {
            String response = userDetailsService.registerUser(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}