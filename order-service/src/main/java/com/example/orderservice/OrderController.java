package com.example.orderservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    @GetMapping("/orders")
    public List<Map<String, Object>> getOrders() {
        // Simple static response just to verify service is running
        return List.of(
                Map.of("id", 100, "userId", 1, "total", 49.99),
                Map.of("id", 101, "userId", 2, "total", 19.99)
        );
    }

    @GetMapping("/orders/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "service", "order-service");
    }
}

