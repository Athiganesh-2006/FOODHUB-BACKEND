package com.example.FOODHUB.Shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

    List<FoodItem> findByShopId(Long shopId);
    List<FoodItem> findByShopIdAndNameContainingIgnoreCase(Long shopId, String name);
}
