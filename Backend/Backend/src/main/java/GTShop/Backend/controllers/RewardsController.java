package GTShop.Backend.controllers;

import GTShop.Backend.dtos.RewardDTO;
import GTShop.Backend.services.RewardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rewards")
public class RewardsController {

    private final RewardService rewardService;

    public RewardsController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping
    public ResponseEntity<Map<String, List<RewardDTO>>> getRewards() {

        List<RewardDTO> rewardsList = rewardService.getAllRewards();

        return ResponseEntity.ok(Map.of("rewards", rewardsList));
    }
}