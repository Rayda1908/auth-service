package com.example.Auth_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

public class SecurityConfig{
    //// Registers BCrypt as the application-wide password hashing bean
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //// Configures endpoint security rules and authentication methods
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception {
        // Disable CSRF & frame options so the H2 web console can load in the browser
        // h2 : a fake temporary database that runs inside my computer's RAM
        http
           .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))

                // Define access rules for URL paths
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // Allow public access to H2 UI
                        .requestMatchers("/admin/**").hasRole("ADMIN")   // Only users with ROLE_ADMIN
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // Both roles allowed
                        .anyRequest().authenticated()                   // All other requests require login
                )
                // Enable default HTTP Basic and standard login form
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());

        return http.build();
    }
}


