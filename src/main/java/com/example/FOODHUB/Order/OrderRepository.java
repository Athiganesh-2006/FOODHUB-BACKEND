package com.example.FOODHUB.Order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // Get customer orders
    List<Order> findByCustomerIdOrderByCreatedAtDesc(Long customerId);

    // Get shop orders
    List<Order> findByShopIdOrderByCreatedAtDesc(Long shopId);
}