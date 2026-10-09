package GTShop.Backend.services;

import GTShop.Backend.dtos.CartItemDTO;
import GTShop.Backend.dtos.CartResponseDTO;
import GTShop.Backend.dtos.RewardCartDTO;
import GTShop.Backend.entities.Cart;
import GTShop.Backend.entities.CartItem;
import GTShop.Backend.entities.Reward;
import GTShop.Backend.repositories.CartRepository;
import GTShop.Backend.repositories.RewardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final RewardRepository rewardRepository;

    public CartService(CartRepository cartRepository, RewardRepository rewardRepository) {
        this.cartRepository = cartRepository;
        this.rewardRepository = rewardRepository;
    }

    @Transactional
    public CartResponseDTO addItemToCart(String sessionId, RewardCartDTO request) {

        Cart cart = cartRepository.findBySessionId(sessionId)
                .orElseGet(() -> new Cart(sessionId));

        Reward reward = rewardRepository.findById(request.rewardId())
                .orElseThrow(() -> new RuntimeException("Reward not found with ID: " + request.rewardId()));

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getReward().getId().equals(reward.getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + request.quantity());
        } else {
            cart.addItem(new CartItem(reward, request.quantity()));
        }

        Cart savedCart = cartRepository.save(cart);

        return mapToCartResponseDTO(savedCart);
    }

    public CartResponseDTO getCartBySessionId(String sessionId) {
        Cart cart = cartRepository.findBySessionId(sessionId)
                .orElseGet(() -> new Cart(sessionId));
        return mapToCartResponseDTO(cart);
    }

    private CartResponseDTO mapToCartResponseDTO(Cart cart) {
        List<CartItemDTO> itemDTOs = cart.getItems().stream()
                .map(item -> new CartItemDTO(
                        item.getReward().getId(),
                        item.getReward().getName(),
                        item.getQuantity(),
                        item.getReward().getPrice(),
                        item.getQuantity() * item.getReward().getPrice()
                ))
                .toList();

        int totalPoints = itemDTOs.stream()
                .mapToInt(CartItemDTO::subtotalPoints)
                .sum();

        return new CartResponseDTO(cart.getSessionId(), itemDTOs, totalPoints);
    }
}