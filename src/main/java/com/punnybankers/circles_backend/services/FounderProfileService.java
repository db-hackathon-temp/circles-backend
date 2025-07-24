import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FounderProfileService {
    private final FounderProfileRepository founderRepo;
    private final UserRepository userRepo; // Assume you have this

    public FounderProfileService(FounderProfileRepository founderRepo, UserRepository userRepo) {
        this.founderRepo = founderRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public FounderProfile createFounderProfile(FounderProfileRequest req) {
        User user = userRepo.findById(req.getUserId())
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
}
