package com.example.Auth_service.repository;

import com.example.Auth_service.entity.User;

// Imports Spring Data JPA's standard repository interface providing complete CRUD(create, read, update, delete) operations
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


//// Extending JpaRepository<User, Long> provides ready-to-use methods like save(), findAll(), deleteById()
////'User' is the entity being managed; 'Long' matches the datatype of its primary key (@Id)

public interface UserRepository extends JpaRepository<User, Long> {

    //// Spring Data JPA inspects this method name at startup and automatically generates the SQL:
    //// Wrapping the result in Optional<User> allows us to safely check whether a matching record was found

    Optional<User> findByUsername(String username);
}