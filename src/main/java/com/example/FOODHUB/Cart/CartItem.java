package com.example.FOODHUB.Cart;


import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @Column(nullable = false)
    private Long foodItemId;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double price;

    public CartItem() {
    }

    public CartItem(
            Cart cart,
            Long foodItemId,
            int quantity,
            double price
    ) {
        this.cart = cart;
        this.foodItemId = foodItemId;
        this.quantity = quantity;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setFoodItemId(Long foodItemId) {
        this.foodItemId = foodItemId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}