package GTShop.Backend.repositories;


import GTShop.Backend.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {
    Optional<UserProfile> findBySessionId(String sessionId);
}
