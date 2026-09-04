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

  

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {

        return ResponseEntity.ok(
                orderService.getAllOrders()
        );
    }

    

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

    

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                orderService.getOrderById(id)
        );
    }

   
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> getCustomerOrders(
            @PathVariable Long customerId
    ) {

        return ResponseEntity.ok(
                orderService.getCustomerOrders(customerId)
        );
    }

    

    @GetMapping("/shop/{shopId}")
    public ResponseEntity<List<Order>> getShopOrders(
            @PathVariable Long shopId
    ) {

        return ResponseEntity.ok(
                orderService.getShopOrders(shopId)
        );
    }

    

    @PatchMapping("/{id}/status")
    public ResponseEntity<Order> updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> request
    ) {

        String status = request.get("status");

        
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