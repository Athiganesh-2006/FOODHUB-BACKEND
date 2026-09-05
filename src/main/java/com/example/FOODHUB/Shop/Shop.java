package com.example.FOODHUB.Shop;

import jakarta.persistence.*;
<<<<<<< HEAD:src/main/java/com/example/FOODHUB/Shop/Shop.java
import lombok.Data;
=======
>>>>>>> a7e9cb6209c035dc130c1492f9350715849b1b81:src/main/java/com/example/FOODHUB/Shop/entity/Shop.java

@Entity
@Table(name = "shops")
@Data

public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String image;

    private boolean open;

    public Shop() {
    }

<<<<<<< HEAD:src/main/java/com/example/FOODHUB/Shop/Shop.java

    // getters and setters
}
=======
    // Getters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public boolean isOpen() {
        return open;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
>>>>>>> a7e9cb6209c035dc130c1492f9350715849b1b81:src/main/java/com/example/FOODHUB/Shop/entity/Shop.java
