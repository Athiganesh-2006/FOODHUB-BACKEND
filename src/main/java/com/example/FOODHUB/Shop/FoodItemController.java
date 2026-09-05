package com.example.FOODHUB.Shop;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/fooditems")
public class FoodItemController {

    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @GetMapping
    public List<FoodItem> getAllFoodItems() {
        return foodItemService.getAllFoodItems();
    }

    @PostMapping("/shop/{shopId}")
    public FoodItem addFoodToShop(
            @PathVariable Long shopId,
            @RequestBody FoodItem foodItem) {

        return foodItemService.addFoodToShop(shopId, foodItem);
    }
    @GetMapping("/shop/{shopId}")
    public List<FoodItem> getFoodsByShop(@PathVariable Long shopId) {

        return foodItemService.getFoodsByShop(shopId);
    }
    @GetMapping("/shop/{shopId}/search")
    public List<FoodItem> searchFoodInShop(
            @PathVariable Long shopId,
            @RequestParam String name) {

        return foodItemService.searchFoodInShop(shopId, name);
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