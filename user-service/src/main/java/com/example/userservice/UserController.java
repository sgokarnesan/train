package com.example.userservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @GetMapping("/users")
    public List<Map<String, Object>> getUsers() {
        // Simple static response just to verify service is running
        return List.of(
                Map.of("id", 1, "name", "Alice"),
                Map.of("id", 2, "name", "Bob")
        );
    }

    @GetMapping("/users/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "service", "user-service");
    }
}

