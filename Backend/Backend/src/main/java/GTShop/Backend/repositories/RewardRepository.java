package GTShop.Backend.repositories;

import GTShop.Backend.entities.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Integer> { }
