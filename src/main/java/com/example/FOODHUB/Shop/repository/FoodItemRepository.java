package com.example.FOODHUB.Shop.repository;

import com.example.FOODHUB.Shop.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    List<FoodItem> findByShopIdAndAvailableTrue(Long shopId);
}
