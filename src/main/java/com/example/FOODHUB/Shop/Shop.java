package com.example.FOODHUB.Shop;

import jakarta.persistence.*;
import lombok.Data;

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


    // getters and setters
}
