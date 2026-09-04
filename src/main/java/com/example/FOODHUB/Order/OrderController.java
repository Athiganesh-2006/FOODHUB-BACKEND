package com.example.FOODHUB.Order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // =========================================================
    // GET ALL ORDERS
    // GET http://localhost:8081/api/orders
    // =========================================================

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {

        return ResponseEntity.ok(
                orderService.getAllOrders()
        );
    }

    // =========================================================
    // PLACE ORDER
    // POST http://localhost:8081/api/orders
    // =========================================================

    @PostMapping
    public ResponseEntity<Order> placeOrder(
            @RequestBody PlaceOrderRequest request
    ) {

        Order order = orderService.placeOrder(request);

        return new ResponseEntity<>(
                order,
                HttpStatus.CREATED
        );
    }

    // =========================================================
    // GET ORDER BY ID
    // GET http://localhost:8081/api/orders/1
    // =========================================================

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                orderService.getOrderById(id)
        );
    }

    // =========================================================
    // GET CUSTOMER ORDERS
    // GET http://localhost:8081/api/orders/customer/1
    // =========================================================

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getCustomerOrders(
            @PathVariable Long customerId
    ) {

        return ResponseEntity.ok(
                orderService.getCustomerOrders(customerId)
        );
    }

    // =========================================================
    // GET SHOP ORDERS
    // GET http://localhost:8081/api/orders/shop/1
    // =========================================================

    @GetMapping("/shop/{shopId}")
    public ResponseEntity<List<Order>> getShopOrders(
            @PathVariable Long shopId
    ) {

        return ResponseEntity.ok(
                orderService.getShopOrders(shopId)
        );
    }

    // =========================================================
    // UPDATE ORDER STATUS
    // PATCH http://localhost:8081/api/orders/1/status
    // =========================================================

    @PatchMapping("/{id}/status")
    public ResponseEntity<Order> updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> request
    ) {

        String status = request.get("status");

        // Status missing
        if (status == null || status.isBlank()) {

            return ResponseEntity.badRequest().build();
        }

        OrderStatus orderStatus;

        try {

            orderStatus =
                    OrderStatus.valueOf(
                            status.toUpperCase()
                    );

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(
                orderService.updateStatus(
                        id,
                        orderStatus
                )
        );
    }
}