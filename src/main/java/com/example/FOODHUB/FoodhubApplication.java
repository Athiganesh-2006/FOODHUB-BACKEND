package com.example.FOODHUB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.example.FOODHUB.Shop.entity.FoodItem;
import com.example.FOODHUB.Shop.entity.Shop;
import com.example.FOODHUB.Shop.repository.FoodItemRepository;
import com.example.FOODHUB.Shop.repository.ShopRepository;

@SpringBootApplication
public class FoodhubApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodhubApplication.class, args);
	}

	@Bean
	CommandLineRunner seedData(ShopRepository shops, FoodItemRepository items) {
		return args -> {
			if (shops.count() > 0) return;
			Shop pizza = new Shop();
			pizza.setName("Crust & Craft");
			pizza.setDescription("Hand-stretched pizzas and fresh salads");
			pizza.setImage("https://images.unsplash.com/photo-1579751626657-72bc17010498?auto=format&fit=crop&w=900&q=80");
			pizza.setOpen(true);
			pizza = shops.save(pizza);

			FoodItem margherita = new FoodItem();
			margherita.setShopId(pizza.getId());
			margherita.setName("Garden Margherita");
			margherita.setDescription("Tomato, basil, mozzarella, olive oil");
			margherita.setPrice(12.50);
			items.save(margherita);

			FoodItem spicy = new FoodItem();
			spicy.setShopId(pizza.getId());
			spicy.setName("Calabrian Heat");
			spicy.setDescription("Spicy salami, peppers, mozzarella, honey");
			spicy.setPrice(15.00);
			items.save(spicy);
		};
	}

}
