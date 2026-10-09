package GTShop.Backend.controllers;

import GTShop.Backend.dtos.CartResponseDTO;
import GTShop.Backend.dtos.RewardCartDTO;
import GTShop.Backend.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<CartResponseDTO> addToCart(
            @RequestHeader("Session-Id") String sessionId,
            @RequestBody RewardCartDTO request) {

        CartResponseDTO updatedCart = cartService.addItemToCart(sessionId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(updatedCart);
    }

    @GetMapping
    public ResponseEntity<CartResponseDTO> getCart(
            @RequestHeader("Session-Id") String sessionId) {

        CartResponseDTO currentCart = cartService.getCartBySessionId(sessionId);

        return ResponseEntity.ok(currentCart);
    }
}