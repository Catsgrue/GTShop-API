package GTShop.Backend.dtos;

import java.util.List;

public record CartResponseDTO(
        String sessionId,
        List<CartItemDTO> items,
        Integer totalPoints
) {}