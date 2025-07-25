package com.punnybankers.circles_backend.services;

import com.punnybankers.circles_backend.controllers.UserController;
import com.punnybankers.circles_backend.models.SharkProfileRequest;
import com.punnybankers.circles_backend.repositories.SharkProfileRepository;
import com.punnybankers.circles_backend.repositories.UserRepository;
import com.punnybankers.circles_backend.repositories.entities.FounderProfile;
import com.punnybankers.circles_backend.repositories.entities.SharkProfile;
import com.punnybankers.circles_backend.repositories.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class SharkProfileService {
    private final SharkProfileRepository sharkRepo;
    private final UserRepository userRepo;

    private final UserController userController;

    public SharkProfileService(SharkProfileRepository sharkRepo, UserRepository userRepo, UserController userController) {
        this.sharkRepo = sharkRepo;
        this.userRepo = userRepo;
        this.userController = userController;
    }

    @Transactional
    public SharkProfile createSharkProfile(SharkProfileRequest req) {
        String username = userController.getUsername(req.getToken());
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        SharkProfile profile = SharkProfile.builder()
                .user(user)
                .monthlyCommitment(req.getMonthlyCommitment())
                .sectorPreference(req.getSectorPreference())
                .preferredCountries(req.getPreferredCountries())
                .payoutMode(req.getPayoutMode())
                .agreementSigned(req.getAgreementSigned())
                .participationContract(req.getParticipationContract())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        return sharkRepo.save(profile);
    }

    public List<SharkProfile> getAllSharks() {
        return new ArrayList<>(sharkRepo.findAll());
    }
}
