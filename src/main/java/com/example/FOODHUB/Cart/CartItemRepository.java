package com.example.FOODHUB.Cart;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartIdAndFoodItemId(
            Long cartId,
            Long foodItemId
    );
}