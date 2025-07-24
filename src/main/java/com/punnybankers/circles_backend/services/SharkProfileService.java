package com.punnybankers.circles_backend.services;

import com.punnybankers.circles_backend.models.SharkProfileRequest;
import com.punnybankers.circles_backend.repositories.SharkProfileRepository;
import com.punnybankers.circles_backend.repositories.UserRepository;
import com.punnybankers.circles_backend.repositories.entities.SharkProfile;
import com.punnybankers.circles_backend.repositories.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class SharkProfileService {
    private final SharkProfileRepository sharkRepo;
    private final UserRepository userRepo;

    public SharkProfileService(SharkProfileRepository sharkRepo, UserRepository userRepo) {
        this.sharkRepo = sharkRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public SharkProfile createSharkProfile(SharkProfileRequest req) {
        User user = userRepo.findById(req.getUserId())
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
}
