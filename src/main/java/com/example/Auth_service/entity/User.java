package com.example.Auth_service.entity;

// jakarta.persistence imports all Jakarta Persistence API (JPA) annotations (such as @Entity, @Id, @Column, @Table)
import jakarta.persistence.*;
import org.springframework.expression.spel.ast.NullLiteral;

@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Configures the database to auto-increment the ID number (1, 2, 3...) when a new row is inserted
    private Long id;

    @Column(nullable = false, unique = true) // Maps this field to a column that cannot be null and prevents duplicate usernames across the entire table
    private String username;

    @Column(nullable = false)  // Maps this field to a non-null column where we will store the BCrypt-hashed password string
    private String password;

    @Column(nullable = false) // Maps this field to store role authorities for authorization checks (is it a normal user or an admin)
    private String role;

    public User(){
    }
    public User(String username, String password, String role)   {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public Long getId(){
        return id;
    }
    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getRole(){
        return role;
    }
    public void setRole(String role){
        this.role=role;
    }

}



