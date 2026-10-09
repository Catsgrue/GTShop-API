package GTShop.Backend.dtos;

import java.util.List;

public record UserProfileDTO(
        String sessionId,
        Integer activityPoints,
        List<UserHistoryDTO> history
) {}