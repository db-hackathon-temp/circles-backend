package com.punnybankers.circles_backend.controllers;

import com.punnybankers.circles_backend.models.FounderProfileRequest;
import com.punnybankers.circles_backend.repositories.entities.Contribution;
import com.punnybankers.circles_backend.repositories.entities.FounderProfile;
import com.punnybankers.circles_backend.services.FounderProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/founder/profile")
public class FounderProfileController {

    private final FounderProfileService founderProfileService;

    public FounderProfileController(FounderProfileService founderProfileService) {
        this.founderProfileService = founderProfileService;
    }

    @PostMapping
    public ResponseEntity<FounderProfile> createFounderProfile(@RequestBody FounderProfileRequest request) {
        FounderProfile created = founderProfileService.createFounderProfile(request);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/details")
    public ResponseEntity<?> getAllFounders() {
        try {
            List<FounderProfile> founders = founderProfileService.getAllFounders();
            return ResponseEntity.ok(founders);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid circle id or no contributions for circle");
        }
    }

    @GetMapping("/score")
    public ResponseEntity<?> getFounderScore(@RequestBody FounderProfileRequest request) {
        try {
            Integer score = founderProfileService.generateFounderScore(request);
            return ResponseEntity.ok(score);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid circle id or no contributions for circle");
        }
    }



}
