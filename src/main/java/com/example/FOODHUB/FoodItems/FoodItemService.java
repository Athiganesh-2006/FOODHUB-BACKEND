package com.example.FOODHUB.FoodItems;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;

    public FoodItemService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    // Add food item
    public FoodItem addFoodItem(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    // Get all food items
    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
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

    public void deleteFoodItem(Long id) {
        foodItemRepository.deleteById(id);
    }
}