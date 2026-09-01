package com.example.FOODHUB.Shop.controller;

import com.example.FOODHUB.Shop.entity.Shop;
import com.example.FOODHUB.Shop.service.ShopService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shops")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    // Create Shop
    @PostMapping
    public Shop createShop(@RequestBody Shop shop) {
        return shopService.createShop(shop);
    }

    // Get Shop by ID
    @GetMapping("/{id}")
    public Shop getShopById(@PathVariable Long id) {
        return shopService.getShopById(id);
    }

    // Get all Shops
    @GetMapping
    public List<Shop> getAllShops() {
        return shopService.getAllShops();
    }

    // Delete Shop
    @DeleteMapping("/{id}")
    public String deleteShop(@PathVariable Long id) {
        shopService.deleteShop(id);
        return "Shop deleted successfully";
    }
}