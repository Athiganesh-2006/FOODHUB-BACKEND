package com.example.FOODHUB.Shop.controller;

import com.example.FOODHUB.Shop.entity.FoodItem;
import com.example.FOODHUB.Shop.repository.FoodItemRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/food-items")
@CrossOrigin(origins = "http://localhost:5173")
public class FoodItemController {
    private final FoodItemRepository foodItemRepository;

    public FoodItemController(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    @GetMapping("/shop/{shopId}")
    public List<FoodItem> getAvailableItems(@PathVariable Long shopId) {
        return foodItemRepository.findByShopIdAndAvailableTrue(shopId);
    }

    @PostMapping
    public FoodItem createItem(@RequestBody FoodItem item) {
        return foodItemRepository.save(item);
    }
}
