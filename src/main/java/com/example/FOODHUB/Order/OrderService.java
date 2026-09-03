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


    // PLACE ORDER
    @Transactional
    public Order placeOrder(PlaceOrderRequest request) {

        Order order = new Order();

        order.setCartId(request.getCartId());
        order.setOrderStatus("PENDING");
        order.setOrderDate(LocalDateTime.now());

        double totalAmount = 0;

        if (request.getItems() != null) {

            for (OrderItem item : request.getItems()) {

                item.setOrder(order);

                double itemTotal =
                        item.getPrice() * item.getQuantity();

                totalAmount += itemTotal;

                order.getItems().add(item);
            }
        }

        order.setTotalAmount(totalAmount);

        return orderRepository.save(order);
    }


    // GET ALL ORDERS
    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }


    // GET ORDER BY ID
    public Order getOrderById(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order not found with ID: " + id
                        )
                );
    }


    // UPDATE ORDER STATUS
    public Order updateOrderStatus(
            Long id,
            String status
    ) {

        Order order = getOrderById(id);

        order.setOrderStatus(status);

        return orderRepository.save(order);
    }


    // DELETE ORDER
    public void deleteOrder(Long id) {

        Order order = getOrderById(id);

        orderRepository.delete(order);
    }
}