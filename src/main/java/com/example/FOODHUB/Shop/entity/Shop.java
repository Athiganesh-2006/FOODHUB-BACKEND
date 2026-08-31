package com.example.FOODHUB.Shop.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "shops")
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