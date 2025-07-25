package com.punnybankers.circles_backend.services;

import com.punnybankers.circles_backend.controllers.UserController;
import com.punnybankers.circles_backend.models.FounderProfileRequest;
import com.punnybankers.circles_backend.repositories.FounderProfileRepository;
import com.punnybankers.circles_backend.repositories.UserRepository;
import com.punnybankers.circles_backend.repositories.entities.Circle;
import com.punnybankers.circles_backend.repositories.entities.FounderProfile;
import com.punnybankers.circles_backend.repositories.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FounderProfileService {
    private final FounderProfileRepository founderRepo;
    private final UserRepository userRepo; // Assume you have this

    private final UserController userController;

    public FounderProfileService(FounderProfileRepository founderRepo, UserRepository userRepo, UserController userController) {
        this.founderRepo = founderRepo;
        this.userRepo = userRepo;
        this.userController = userController;
    }

    @Transactional
    public FounderProfile createFounderProfile(FounderProfileRequest req) {
        String username = userController.getUsername(req.getToken());
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        FounderProfile profile = FounderProfile.builder()
                .user(user)
                .businessName(req.getBusinessName())
                .businessDetails(req.getBusinessDetails())
                .pitchUrl(req.getPitchUrl())
                .fundingGoal(req.getFundingGoal())
                .agreementSigned(req.getAgreementSigned())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        return founderRepo.save(profile);
    }

    public List<FounderProfile> getAllFounders() {
        return new ArrayList<>(founderRepo.findAll());
    }

    public Integer generateFounderScore(FounderProfileRequest req) {
        // Simple heuristic as placeholder for LLaMA AI scoring
        List<String> positiveKeywords = Arrays.asList("innovative", "trusted", "reliable", "transparent", "secure");
        int keywordCount = 0;
        String business = req.getBusinessName();
        String businessDetails = req.getBusinessDetails();
        String combined = (business + " " + businessDetails).toLowerCase();
        for (String kw : positiveKeywords) {
            if (combined.contains(kw)) keywordCount++;
        }

        Integer baseScore = 50;
        Integer lengthScore = (int) Math.min((business.length() + businessDetails.length()) / 10.0, 40);
        Integer keywordScore = keywordCount * 10;

        Integer total = baseScore + lengthScore + keywordScore;
        return Math.min(total, 100);

    }
}
