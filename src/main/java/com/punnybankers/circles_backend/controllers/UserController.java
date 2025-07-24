package com.punnybankers.circles_backend.controllers;

import com.punnybankers.circles_backend.config.JwtUtil;
import com.punnybankers.circles_backend.repositories.entities.User;
import com.punnybankers.circles_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
//        Optional<User> user = authService.authenticate(username, password);
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent()) {
            String token = jwtUtil.generateToken(user.get());
            return ResponseEntity.ok().body(token);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @GetMapping("/user-details")
    public ResponseEntity<?> getUserDetails(@RequestHeader("Authorization") String token) {
//        if (jwtUtil.validateToken(token)) {
//            String username = jwtUtil.extractUsername(token);
//            Optional<User> user = userService.findByUsername(username);
//            return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).body("User not found"));
//        }
        return ResponseEntity.status(401).body("Invalid or expired token");
    }
}

