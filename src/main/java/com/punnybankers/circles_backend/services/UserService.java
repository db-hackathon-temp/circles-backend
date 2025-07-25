package com.punnybankers.circles_backend.services;


import com.punnybankers.circles_backend.models.UserRequest;
import com.punnybankers.circles_backend.repositories.UserRepository;
import com.punnybankers.circles_backend.repositories.entities.Status;
import com.punnybankers.circles_backend.repositories.entities.User;
import com.punnybankers.circles_backend.repositories.entities.UserRole;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User authenticateUser(String username, String password) {
        User user = findByUsername(username);
        if (password.equals(user.getPassword())) {
            return user;
        } else {
            throw new RuntimeException("Password is invalid");
        }
    }


    public Optional<User> findById(UUID userId) {
        return userRepository.findById(userId);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public User registerUser(UserRequest userRequest) {
        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .dateOfBirth(userRequest.getDateOfBirth())
                .password(userRequest.getPassword())
                .mobile(userRequest.getMobile())
                .role(UserRole.valueOf(userRequest.getRole().toUpperCase()))
                .kycStatus(Status.PENDING)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
        return userRepository.save(user);
    }

    public List<User> findAllUserByRole(String role) {
        return userRepository.findAllByRole(UserRole.valueOf(role.toUpperCase()));
    }
}
