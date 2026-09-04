package com.example.FOODHUB.Order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // =========================================================
    // GET ALL ORDERS
    // =========================================================

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // =========================================================
    // PLACE ORDER
    // =========================================================

    @Transactional
    public Order placeOrder(PlaceOrderRequest request) {

        // Validate customer
        if (request.getCustomerId() == null) {
            throw new RuntimeException("Customer ID is required");
        }

        // Validate shop
        if (request.getShopId() == null) {
            throw new RuntimeException("Shop ID is required");
        }

        // Validate items
        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new RuntimeException("Order must contain at least one item");
        }

        // Create Order
        Order order = new Order();

        order.setCustomerId(request.getCustomerId());
        order.setShopId(request.getShopId());

        // Default order type
        if (request.getOrderType() == null ||
                request.getOrderType().isBlank()) {

            order.setOrderType("DELIVERY");

        } else {

            order.setOrderType(request.getOrderType());
        }

        // Default status
        order.setStatus(OrderStatus.PENDING);

        // Current date/time
        order.setCreatedAt(LocalDateTime.now());

        // Calculate total
        double totalAmount = 0.0;

        // Add items
        for (PlaceOrderRequest.ItemRequest itemRequest : request.getItems()) {

            // Validate food item
            if (itemRequest.getFoodItemId() == null) {
                throw new RuntimeException("Food Item ID is required");
            }

            // Validate quantity
            if (itemRequest.getQuantity() <= 0) {
                throw new RuntimeException(
                        "Quantity must be greater than zero"
                );
            }

            // Validate price
            if (itemRequest.getPrice() < 0) {
                throw new RuntimeException(
                        "Price cannot be negative"
                );
            }

            // Create OrderItem
            OrderItem item = new OrderItem();

            item.setFoodItemId(itemRequest.getFoodItemId());
            item.setQuantity(itemRequest.getQuantity());
            item.setPrice(itemRequest.getPrice());

            // Calculate item total
            double itemTotal =
                    itemRequest.getPrice()
                            * itemRequest.getQuantity();

            totalAmount += itemTotal;

            // Add item to order
            order.addItem(item);
        }

        // Set total
        order.setTotalAmount(totalAmount);

        // Save order
        return orderRepository.save(order);
    }

    // =========================================================
    // GET ORDER BY ID
    // =========================================================

    public Order getOrderById(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order not found: " + id
                        )
                );
    }

    // =========================================================
    // GET CUSTOMER ORDERS
    // =========================================================

    public List<Order> getCustomerOrders(Long customerId) {

        return orderRepository
                .findByCustomerIdOrderByCreatedAtDesc(customerId);
    }

    // =========================================================
    // GET SHOP ORDERS
    // =========================================================

    public List<Order> getShopOrders(Long shopId) {

        return orderRepository
                .findByShopIdOrderByCreatedAtDesc(shopId);
    }

    // =========================================================
    // UPDATE ORDER STATUS
    // =========================================================

    @Transactional
    public Order updateStatus(
            Long orderId,
            OrderStatus newStatus
    ) {

        // Find order
        Order order = getOrderById(orderId);

        OrderStatus currentStatus = order.getStatus();

        // PENDING -> ACCEPTED
        if (currentStatus == OrderStatus.PENDING
                && newStatus == OrderStatus.ACCEPTED) {

            order.setStatus(OrderStatus.ACCEPTED);
        }

        // PENDING -> REJECTED
        else if (currentStatus == OrderStatus.PENDING
                && newStatus == OrderStatus.REJECTED) {

            order.setStatus(OrderStatus.REJECTED);
        }

        // ACCEPTED -> COMPLETED
        else if (currentStatus == OrderStatus.ACCEPTED
                && newStatus == OrderStatus.COMPLETED) {

            order.setStatus(OrderStatus.COMPLETED);
        }

        // PENDING -> CANCELLED
        else if (currentStatus == OrderStatus.PENDING
                && newStatus == OrderStatus.CANCELLED) {

            order.setStatus(OrderStatus.CANCELLED);
        }

        // Invalid status
        else {

            throw new RuntimeException(
                    "Invalid status change from "
                            + currentStatus
                            + " to "
                            + newStatus
            );
        }

        return orderRepository.save(order);
    }
}