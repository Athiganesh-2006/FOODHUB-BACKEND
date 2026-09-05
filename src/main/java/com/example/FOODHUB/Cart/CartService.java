package com.example.FOODHUB.Cart;



import com.example.FOODHUB.Cart.Cart;
import com.example.FOODHUB.Cart.CartItem;
import com.example.FOODHUB.Cart.CartItemRepository;
import com.example.FOODHUB.Cart.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public CartService(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository
    ) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public Cart getCart(Long customerId) {

        return cartRepository.findByCustomerId(customerId)
                .orElseGet(() ->
                        cartRepository.save(
                                new Cart(customerId)
                        )
                );
    }

    public Cart addItem(
            Long customerId,
            Long foodItemId,
            int quantity,
            double price
    ) {

        if (quantity <= 0) {
            throw new RuntimeException(
                    "Quantity must be greater than 0"
            );
        }

        Cart cart = getCart(customerId);

        var existingItem =
                cartItemRepository
                        .findByCartIdAndFoodItemId(
                                cart.getId(),
                                foodItemId
                        );

        if (existingItem.isPresent()) {

            CartItem item = existingItem.get();

            item.setQuantity(
                    item.getQuantity() + quantity
            );

            item.setPrice(price);

            cartItemRepository.save(item);

        } else {

            CartItem item = new CartItem();

            item.setCart(cart);
            item.setFoodItemId(foodItemId);
            item.setQuantity(quantity);
            item.setPrice(price);

            cartItemRepository.save(item);
        }

        calculateTotal(cart);

        return cartRepository.save(cart);
    }

    public Cart updateQuantity(
            Long itemId,
            int quantity
    ) {

        CartItem item =
                cartItemRepository.findById(itemId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Cart item not found"
                                )
                        );

        Cart cart = item.getCart();

        if (quantity <= 0) {

            cartItemRepository.delete(item);

        } else {

            item.setQuantity(quantity);

            cartItemRepository.save(item);
        }

        calculateTotal(cart);

        return cartRepository.save(cart);
    }

    public Cart removeItem(Long itemId) {

        CartItem item =
                cartItemRepository.findById(itemId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Cart item not found"
                                )
                        );

        Cart cart = item.getCart();

        cartItemRepository.delete(item);

        calculateTotal(cart);

        return cartRepository.save(cart);
    }

    public void clearCart(Long customerId) {

        Cart cart =
                cartRepository.findByCustomerId(customerId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Cart not found"
                                )
                        );

        cartItemRepository.deleteAll(cart.getItems());

        cart.getItems().clear();

        cart.setTotalAmount(0.0);

        cartRepository.save(cart);
    }

    public double calculateTotal(Cart cart) {

        double total = 0.0;

        for (CartItem item : cart.getItems()) {

            total +=
                    item.getPrice() *
                            item.getQuantity();
        }

        cart.setTotalAmount(total);

        return total;
    }
}