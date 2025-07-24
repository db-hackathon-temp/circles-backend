import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface FounderProfileRepository extends JpaRepository<FounderProfile, UUID> { }
