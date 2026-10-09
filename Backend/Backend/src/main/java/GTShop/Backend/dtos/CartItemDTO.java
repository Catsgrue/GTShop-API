package GTShop.Backend.dtos;

public record CartItemDTO(
        Long rewardId,
        String rewardName,
        Integer quantity,
        Integer pointsPerItem,
        Integer subtotalPoints
) {}