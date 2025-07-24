package com.punnybankers.circles_backend.controllers;

import com.punnybankers.circles_backend.models.CircleRequest;
import com.punnybankers.circles_backend.repositories.entities.Circle;
import com.punnybankers.circles_backend.services.CircleService;
import com.punnybankers.circles_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/circle")
public class CircleController {

    @Autowired
    private CircleService circleService;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getUserCircles(@RequestHeader("Authorization") String token) {
        try {
            List<Circle> circles = circleService.getAllCirclesByToken(token);
            return ResponseEntity.ok(circles);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid token or user not found");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCircle(@RequestBody CircleRequest request) {
        try {
            Circle circle = circleService.createCircle(request);
            return ResponseEntity.status(201).body(circle);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

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


    @PostMapping("/contribute")
    public ResponseEntity<?> contribute(@RequestParam String username, @RequestParam UUID circleId) {
        circleService.contribute(username, circleId);
        return ResponseEntity.status(201).body("Contribution saved");
    }

    /*@GetMapping("/contributions")
    public ResponseEntity<?> getContributions(@RequestParam String username) {
        return ResponseEntity.status(401).body("Invalid or expired token");
    }*/
}
