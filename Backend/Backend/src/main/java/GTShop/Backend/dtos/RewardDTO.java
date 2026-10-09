package GTShop.Backend.dtos;

public record RewardDTO (
        Long id,
        String name,
        String description,
        String imageUrl,
        int price,
        String category,
        boolean inStock,
        int stockCount
) {}
