package GTShop.Backend.controllers;

import GTShop.Backend.dtos.UserProfileDTO;
import GTShop.Backend.services.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDTO> getProfile(@RequestHeader("Session-Id") String sessionId) {

        UserProfileDTO profile = userProfileService.getProfileBySession(sessionId);
        return ResponseEntity.ok(profile);
    }
}