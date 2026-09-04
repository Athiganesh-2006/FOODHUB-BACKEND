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

 

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

   

    @Transactional
    public Order placeOrder(PlaceOrderRequest request) {

        
        if (request.getCustomerId() == null) {
            throw new RuntimeException("Customer ID is required");
        }

        
        if (request.getShopId() == null) {
            throw new RuntimeException("Shop ID is required");
        }

        
        if (request.getItems() == null || request.getItems().isEmpty()) {
            throw new RuntimeException("Order must contain at least one item");
        }

        
        Order order = new Order();

        order.setCustomerId(request.getCustomerId());
        order.setShopId(request.getShopId());

        
        if (request.getOrderType() == null ||
                request.getOrderType().isBlank()) {

            order.setOrderType("DELIVERY");

        } else {

            order.setOrderType(request.getOrderType());
        }

       
        order.setStatus(OrderStatus.PENDING);

        
        order.setCreatedAt(LocalDateTime.now());

        
        double totalAmount = 0.0;

       
        for (PlaceOrderRequest.ItemRequest itemRequest : request.getItems()) {

            
            if (itemRequest.getFoodItemId() == null) {
                throw new RuntimeException("Food Item ID is required");
            }

            
            if (itemRequest.getQuantity() <= 0) {
                throw new RuntimeException(
                        "Quantity must be greater than zero"
                );
            }

            
            if (itemRequest.getPrice() < 0) {
                throw new RuntimeException(
                        "Price cannot be negative"
                );
            }

            
            OrderItem item = new OrderItem();

            item.setFoodItemId(itemRequest.getFoodItemId());
            item.setQuantity(itemRequest.getQuantity());
            item.setPrice(itemRequest.getPrice());

            
            double itemTotal =
                    itemRequest.getPrice()
                            * itemRequest.getQuantity();

            totalAmount += itemTotal;

           
            order.addItem(item);
        }

      
        order.setTotalAmount(totalAmount);

       
        return orderRepository.save(order);
    }

    

    public Order getOrderById(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order not found: " + id
                        )
                );
    }

   

    public List<Order> getCustomerOrders(Long customerId) {

        return orderRepository
                .findByCustomerIdOrderByCreatedAtDesc(customerId);
    }

    

    public List<Order> getShopOrders(Long shopId) {

        return orderRepository
                .findByShopIdOrderByCreatedAtDesc(shopId);
    }

    

    @Transactional
    public Order updateStatus(
            Long orderId,
            OrderStatus newStatus
    ) {

       
        Order order = getOrderById(orderId);

        OrderStatus currentStatus = order.getStatus();

       
        if (currentStatus == OrderStatus.PENDING
                && newStatus == OrderStatus.ACCEPTED) {

            order.setStatus(OrderStatus.ACCEPTED);
        }

        
        else if (currentStatus == OrderStatus.PENDING
                && newStatus == OrderStatus.REJECTED) {

            order.setStatus(OrderStatus.REJECTED);
        }

        
        
        else if (currentStatus == OrderStatus.ACCEPTED
                && newStatus == OrderStatus.COMPLETED) {

            order.setStatus(OrderStatus.COMPLETED);
        }

        
        else if (currentStatus == OrderStatus.PENDING
                && newStatus == OrderStatus.CANCELLED) {

            order.setStatus(OrderStatus.CANCELLED);
        }

        
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