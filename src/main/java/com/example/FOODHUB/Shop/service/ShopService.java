package com.example.FOODHUB.Shop.service;
import com.example.FOODHUB.Shop.entity.Shop;
import java.util.List;

public interface ShopService {


    Shop createShop(Shop shop);

    Shop getShopById(Long id);

    List<Shop> getAllShops();

    void deleteShop(Long id);
}
