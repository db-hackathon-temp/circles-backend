import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SharkProfileRepository extends JpaRepository<SharkProfile, UUID> { }
