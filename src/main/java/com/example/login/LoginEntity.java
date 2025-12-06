package com.example.login;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class LoginEntity {
    @GeneratedValue
    @Id
    Long id;
    String username;
    String password;
    String role;

    public String getRole() {   // <-- MUST exist
        return role;
    }
}
