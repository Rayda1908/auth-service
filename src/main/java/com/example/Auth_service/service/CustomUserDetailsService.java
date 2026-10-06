package com.example.Auth_service.service;

// Imports our custom User database entity
import com.example.Auth_service.entity.User;

// Imports our UserRepository interface
import com.example.Auth_service.repository.UserRepository;

// Import Spring Security's representation
import org.springframework.security.core.authority.SimpleGrantedAuthority;

// import the UserDetails interface
import org.springframework.security.core.userdetails.UserDetails;

// import the core spring security interface responsible for loading user-specific data
import org.springframework.security.core.userdetails.UserDetailsService;

//Imports the standard exception thrown when a requested username is not found
import org.springframework.security.core.userdetails.UsernameNotFoundException;

//Marks this class as a Spring Service component so Spring automatically detects it as an injectable bean
import org.springframework.stereotype.Service;

//Imports utility methods for creating single-element lists without extra memory overhead
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Declares an immutable reference to our UserRepository to fetch data from the database
    private final UserRepository userRepository;

    // Constructor injection: Spring automatically passes (injects) the UserRepository instance here at startup
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // This method is invoked by Spring Security automatically whenever someone submits credentials to /login
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Query the database using our repository method findByUsername
        // If the user does not exist in the database, immediately throw UsernameNotFoundException to halt authentication
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Convert our custom database User entity into Spring Security's built-in UserDetails object
        // 1st argument: username
        // 2nd argument: password (the BCrypt-hashed string stored in our database)
        // 3rd argument: collection of granted authorities (roles) wrapped in SimpleGrantedAuthority
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
        );
    }
}