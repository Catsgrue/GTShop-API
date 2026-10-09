package GTShop.Backend.dtos;

import java.util.List;

public record CheckoutResponseDTO(
        boolean success,
        String message,
        Integer newBalance,
        List<String> purchasedItems,
        String transactionId
) {}