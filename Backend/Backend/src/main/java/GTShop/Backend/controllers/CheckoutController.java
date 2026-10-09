package GTShop.Backend.controllers;

import GTShop.Backend.dtos.CheckoutResponseDTO;
import GTShop.Backend.services.CheckoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping
    public ResponseEntity<CheckoutResponseDTO> postCheckout(@RequestHeader("Session-Id") String sessionId) {
        try {
            CheckoutResponseDTO response = checkoutService.processCheckout(sessionId);

            if (!response.success()) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
            }

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new CheckoutResponseDTO(false, e.getMessage(), null, null, null));
        }
    }
}