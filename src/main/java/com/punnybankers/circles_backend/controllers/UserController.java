package com.punnybankers.circles_backend.controllers;

import com.punnybankers.circles_backend.config.JwtUtil;
import com.punnybankers.circles_backend.models.UserRequest;
import com.punnybankers.circles_backend.repositories.entities.User;
import com.punnybankers.circles_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest) {
        User user = userService.registerUser(userRequest);
        String token = jwtUtil.generateToken(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Authorization", token)
                .body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        User user = userService.authenticateUser(username, password);
        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok().body(token);
    }

    @GetMapping("/user-details")
    public ResponseEntity<?> getUserDetails(@RequestHeader("Authorization") String token) {
        String username = getUsername(token);
        if (username != null) {
            User user = userService.findByUsername(username);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(401).body("Invalid or expired token");
    }

    @GetMapping("/usersByRole/{role}")
    public ResponseEntity<?> getUserDetailsByRoles(@RequestParam("role") String role) {
        return ResponseEntity.ok(userService.findAllUserByRole(role));
    }

    public String getUsername(String token) {
        if (jwtUtil.validateToken(token)) {
            return jwtUtil.extractUsername(token);
        } else {
            return null;
        }
    }


}

