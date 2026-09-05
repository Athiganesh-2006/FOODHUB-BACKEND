package com.example.FOODHUB.Shop;
import com.example.FOODHUB.Users.Users;

import java.util.List;

public interface ShopService {


    Shop createShop(Shop shop, Users user);

    Shop getShopById(Long id);

    List<Shop> getAllShops();

    void deleteShop(Long id,Users user);
}
