package GTShop.Backend.dtos;

public record UserHistoryDTO(
        String rewardName,
        Integer pointsSpent,
        String status
) {}
