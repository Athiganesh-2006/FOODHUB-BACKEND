package com.example.FOODHUB.FoodItems;
import com.example.FOODHUB.Shop.entity.Shop;

import jakarta.persistence.*;
import lombok.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="food_items")
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long f_id;


    private String name;
    private String description;
    private double price;
    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;
    private String image;
    private boolean available;


}
