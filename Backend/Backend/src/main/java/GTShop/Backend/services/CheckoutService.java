package GTShop.Backend.services;

import GTShop.Backend.dtos.CheckoutResponseDTO;
import GTShop.Backend.entities.*;
import GTShop.Backend.repositories.CartRepository;
import GTShop.Backend.repositories.UserProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CheckoutService {

    private final CartRepository cartRepository;
    private final UserProfileRepository userProfileRepository;

    public CheckoutService(CartRepository cartRepository, UserProfileRepository userProfileRepository) {
        this.cartRepository = cartRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @Transactional
    public CheckoutResponseDTO processCheckout(String sessionId) {

        Cart cart = cartRepository.findBySessionId(sessionId)
                .orElseThrow(() -> new IllegalStateException("Cart not found for session"));

        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Cart is empty. Cannot proceed to checkout.");
        }

        int totalPointsRequired = cart.getItems().stream()
                .mapToInt(item -> item.getQuantity() * item.getReward().getPrice())
                .sum();

        UserProfile profile = userProfileRepository.findBySessionId(sessionId)
                .orElseGet(() -> userProfileRepository.save(new UserProfile(sessionId)));

        if (profile.getActivityPoints() < totalPointsRequired) {
            return new CheckoutResponseDTO(
                    false,
                    "You don't have enough Activity Points!",
                    profile.getActivityPoints(),
                    List.of(),
                    null
            );
        }

        profile.setActivityPoints(profile.getActivityPoints() - totalPointsRequired);

        List<String> purchasedItemNames = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {
            Reward reward = cartItem.getReward();
            int requestedQuantity = cartItem.getQuantity();

            if (reward.getStockCount() < requestedQuantity) {
                throw new IllegalStateException("Out of stock! Only " + reward.getStockCount() +
                        " items left for: " + reward.getName());
            }

            reward.setStockCount(reward.getStockCount() - requestedQuantity);

            if (reward.getStockCount() <= 0) {
                reward.setInStock(false);
            }

            int pointsSpent = requestedQuantity * reward.getPrice();
            UserHistory historyEntry = new UserHistory(reward.getName(), pointsSpent, "Purchased");
            profile.addHistory(historyEntry);

            purchasedItemNames.add(reward.getName());
        }

        cart.getItems().clear();

        userProfileRepository.save(profile);
        cartRepository.save(cart);

        String transactionId = UUID.randomUUID().toString(); // Generăm un ID unic de tranzacție real
        return new CheckoutResponseDTO(
                true,
                "Your purchase was confirmed!",
                profile.getActivityPoints(),
                purchasedItemNames,
                transactionId
        );
    }
}