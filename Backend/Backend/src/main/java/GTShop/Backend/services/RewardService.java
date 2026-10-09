package GTShop.Backend.services;

import GTShop.Backend.dtos.RewardDTO;
import GTShop.Backend.entities.Reward;
import GTShop.Backend.repositories.RewardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RewardService {

    private final RewardRepository rewardRepository;

    public RewardService(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    public List<RewardDTO> getAllRewards(){

        List<Reward> rewardsFromDb= rewardRepository.findAll();

        return rewardsFromDb.stream().map(this::mapToDTO).toList();
    }

    public RewardDTO mapToDTO(Reward reward){
        return new RewardDTO(reward.getId(),reward.getName(),reward.getDescription(),reward.getImage(),reward.getPrice(),reward.getCategory(),reward.isInStock(),reward.getStockCount());
    }
}
