package com.example.FOODHUB.Shop;
import com.example.FOODHUB.Users.Role;
import com.example.FOODHUB.Users.Users;
import com.example.FOODHUB.Users.UsersRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shops")
public class ShopController {

    private final ShopService shopService;
    private final UsersRepository usersRepository;

    public ShopController(ShopService shopService, UsersRepository usersRepository) {
        this.shopService = shopService;
        this.usersRepository = usersRepository;
    }

    // Create Shop
    @PostMapping
    public Shop createShop(
            @RequestBody Shop shop,
            @RequestParam Long userId) {

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return shopService.createShop(shop, user);
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
    public String deleteShop(
            @PathVariable Long id,
            @RequestParam Long userId) {

        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        shopService.deleteShop(id, user);

        return "Shop deleted successfully";
    }
}