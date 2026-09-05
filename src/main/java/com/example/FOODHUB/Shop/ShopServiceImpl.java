package com.example.FOODHUB.Shop;
import com.example.FOODHUB.Users.Role;
import com.example.FOODHUB.Users.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository) {

        this.shopRepository = shopRepository;
    }
    @Override
    public Shop createShop(Shop shop, Users user) {

        if (user.getRole() == Role.ADMIN) {
            return shopRepository.save(shop);
        }

        throw new RuntimeException("Only Admin can create a shop");
    }

    @Override
    public Shop getShopById(Long id) {
        return shopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shop not found"));
    }

    @Override
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    @Override
    public void deleteShop(Long id, Users user) {

        if (user.getRole() == Role.ADMIN) {
            shopRepository.deleteById(id);
            return;
        }

        throw new RuntimeException("Only Admin can delete a shop");
    }
}