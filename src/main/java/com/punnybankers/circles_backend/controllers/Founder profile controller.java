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
  @GetMapping("/{user_id}")
public ResponseEntity<FounderProfile> getFounderProfile(@PathVariable UUID id) {
    FounderProfile profile = founderProfileService.getFounderProfileById(id);
    return ResponseEntity.ok(profile);
}

}
