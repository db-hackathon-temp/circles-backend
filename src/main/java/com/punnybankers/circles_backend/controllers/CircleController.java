package com.punnybankers.circles_backend.controllers;

import com.punnybankers.circles_backend.repositories.entities.Circle;
import com.punnybankers.circles_backend.repositories.entities.User;
import com.punnybankers.circles_backend.services.CircleService;
import com.punnybankers.circles_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/circle")
public class CircleController {

    @Autowired
    private CircleService circleService;

    @Autowired
    private UserService userService;

    @PostMapping("/add/shark/{circleId}/{userId}")
    public ResponseEntity<?> addSharkToCircle(@PathVariable UUID circleId, @PathVariable UUID userId) {
        boolean success = circleService.addSharkToCircle(circleId, userId);
        if (success) {
            return ResponseEntity.ok("Shark added to circle successfully");
        } else {
            return ResponseEntity.status(404).body("Circle or User not found");
        }
    }

    @PostMapping("/add/member/{circleId}/{userId}")
    public ResponseEntity<?> addMemberToCircle(@PathVariable UUID circleId, @PathVariable UUID userId) {
        boolean success = circleService.addMemberToCircle(circleId, userId);
        if (success) {
            return ResponseEntity.ok("Member added to circle successfully");
        } else {
            return ResponseEntity.status(404).body("Circle or User not found");
        }
    }
}
