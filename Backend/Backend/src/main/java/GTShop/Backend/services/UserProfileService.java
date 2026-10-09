package GTShop.Backend.services;

import GTShop.Backend.dtos.UserProfileDTO;
import GTShop.Backend.dtos.UserHistoryDTO;
import GTShop.Backend.entities.UserProfile;
import GTShop.Backend.repositories.UserProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfileDTO getProfileBySession(String sessionId) {
        UserProfile profile = userProfileRepository.findBySessionId(sessionId)
                .orElseGet(() -> userProfileRepository.save(new UserProfile(sessionId)));

        List<UserHistoryDTO> historyDTOs = profile.getHistory().stream()
                .map(h -> new UserHistoryDTO(h.getRewardName(), h.getPointsSpent(), h.getStatus()))
                .toList();

        return new UserProfileDTO(profile.getSessionId(), profile.getActivityPoints(), historyDTOs);
    }


}