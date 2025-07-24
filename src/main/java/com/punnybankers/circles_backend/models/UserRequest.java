package com.punnybankers.circles_backend.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    private String firstName;
    private String lastName;
    private String username;
    private Instant dateOfBirth;
    private String password;
    private String mobile;
    private String email;
    private String role;
}
