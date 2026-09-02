package com.example.FOODHUB.FoodItems;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/fooditems")
public class FoodItemController {

    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @PostMapping
    public FoodItem addFoodItem(@RequestBody FoodItem foodItem) {
        return foodItemService.addFoodItem(foodItem);
    }

    @GetMapping
    public List<FoodItem> getAllFoodItems() {
        return foodItemService.getAllFoodItems();
    }

    @GetMapping("/{id}")
    public FoodItem getFoodItemById(@PathVariable Long id) {
        return foodItemService.getFoodItemById(id);
    }

    @PutMapping("/{id}/price")
    public FoodItem updatePrice(
            @PathVariable Long id,
            @RequestBody double price) {

        return foodItemService.updatePrice(id, price);
    }

    @PutMapping("/{id}/available")
    public FoodItem setAvailable(
            @PathVariable Long id,
            @RequestBody boolean available) {

        return foodItemService.setAvailable(id, available);
    }

    @PutMapping("/{id}")
    public FoodItem updateFoodItem(
            @PathVariable Long id,
            @RequestBody FoodItem foodItem) {

        return foodItemService.updateFoodItem(id, foodItem);
    }

    @DeleteMapping("/{id}")
    public void deleteFoodItem(@PathVariable Long id) {
        foodItemService.deleteFoodItem(id);
    }
}