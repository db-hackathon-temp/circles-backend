package com.punnybankers.circles_backend.controllers;

import com.punnybankers.circles_backend.models.FounderProfileRequest;
import com.punnybankers.circles_backend.repositories.entities.FounderProfile;
import com.punnybankers.circles_backend.services.FounderProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
