package com.example.FOODHUB.Shop;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;
    private final ShopRepository shopRepository;
    public FoodItemService(
            FoodItemRepository foodItemRepository,
            ShopRepository shopRepository) {

        this.foodItemRepository = foodItemRepository;
        this.shopRepository = shopRepository;
    }

    public FoodItem addFoodToShop(Long shopId, FoodItem foodItem) {

        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new RuntimeException("Shop not found"));

        foodItem.setShop(shop);

        return foodItemRepository.save(foodItem);
    }
    // Get all food items
    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }
    public List<FoodItem> getFoodsByShop(Long shopId) {
        return foodItemRepository.findByShopId(shopId);
    }

    public FoodItem getFoodItemById(Long id) {
        return foodItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food item not found"));
    }


    public FoodItem updatePrice(Long id, double price) {

        FoodItem foodItem = foodItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food item not found"));

        foodItem.setPrice(price);

        return foodItemRepository.save(foodItem);
    }

    public FoodItem setAvailable(Long id, boolean available) {

        FoodItem foodItem = foodItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food item not found"));

        foodItem.setAvailable(available);

        return foodItemRepository.save(foodItem);
    }

    public FoodItem updateFoodItem(Long id, FoodItem foodItem) {
        FoodItem existingFoodItem=foodItemRepository.findById(id).orElseThrow(()->new RuntimeException("Food item not found"));
        existingFoodItem.setName(foodItem.getName());
        existingFoodItem.setDescription(foodItem.getDescription());
        existingFoodItem.setPrice(foodItem.getPrice());
        existingFoodItem.setImage(foodItem.getImage());
        existingFoodItem.setAvailable(foodItem.isAvailable());

        return  foodItemRepository.save(existingFoodItem);

    }

    public List<FoodItem> searchFoodInShop(Long shopId, String name) {
        return foodItemRepository.findByShopIdAndNameContainingIgnoreCase(shopId, name);
    }

    public void deleteFoodItem(Long id) {
        foodItemRepository.deleteById(id);
    }
}