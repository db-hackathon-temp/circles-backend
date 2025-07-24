package com.punnybankers.circles_backend.services;


import com.punnybankers.circles_backend.repositories.entities.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    public Optional<User> findByUsername(String username) {
        if ("johndoe".equals(username)) {
            User user = new User();
            user.setUsername(username);
            user.setFirstName("John");
            user.setLastName("Doe");
            user.setEmail("johndoe@example.com");
            return Optional.of(user);
        }
        return Optional.empty();
    }

}
