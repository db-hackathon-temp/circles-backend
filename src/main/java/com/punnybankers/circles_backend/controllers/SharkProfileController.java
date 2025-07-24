package com.punnybankers.circles_backend.controllers;

import com.punnybankers.circles_backend.models.SharkProfileRequest;
import com.punnybankers.circles_backend.repositories.entities.SharkProfile;
import com.punnybankers.circles_backend.services.SharkProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shark/profile")
public class SharkProfileController {

    private final SharkProfileService sharkProfileService;

    public SharkProfileController(SharkProfileService sharkProfileService) {
        this.sharkProfileService = sharkProfileService;
    }

    @PostMapping
    public ResponseEntity<SharkProfile> createSharkProfile(@RequestBody SharkProfileRequest request) {
        SharkProfile created = sharkProfileService.createSharkProfile(request);
        return ResponseEntity.ok(created);
    }
}
