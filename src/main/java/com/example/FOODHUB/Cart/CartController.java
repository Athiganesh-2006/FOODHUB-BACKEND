package com.example.FOODHUB.Cart;



import com.example.FOODHUB.Cart.Cart;
import com.example.FOODHUB.Cart.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Cart> getCart(
            @PathVariable Long customerId
    ) {

        return ResponseEntity.ok(
                cartService.getCart(customerId)
        );
    }

    @PostMapping("/items")
    public ResponseEntity<Cart> addItem(
            @RequestParam Long customerId,
            @RequestParam Long foodItemId,
            @RequestParam int quantity,
            @RequestParam double price
    ) {

        return ResponseEntity.ok(
                cartService.addItem(
                        customerId,
                        foodItemId,
                        quantity,
                        price
                )
        );
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<Cart> updateQuantity(
            @PathVariable Long itemId,
            @RequestParam int quantity
    ) {

        return ResponseEntity.ok(
                cartService.updateQuantity(
                        itemId,
                        quantity
                )
        );
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Cart> removeItem(
            @PathVariable Long itemId
    ) {

        return ResponseEntity.ok(
                cartService.removeItem(itemId)
        );
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> clearCart(
            @PathVariable Long customerId
    ) {

        cartService.clearCart(customerId);

        return ResponseEntity.ok(
                "Cart cleared successfully"
        );
    }
}
