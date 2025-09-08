package com.example.backend_notes.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_notes.model.User;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @SuppressWarnings("FieldMayBeFinal")
    private Map<String, String> users = new HashMap<>();

    // Register
    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        if (users.containsKey(user.getEmail())) {
            response.put("message", "User already exists");
        } else {
            users.put(user.getEmail(), user.getPassword());
            response.put("token", "dummy-jwt-token");
        }
        return response;
    }

    // Login
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        String storedPassword = users.get(user.getEmail());

        if (storedPassword != null && storedPassword.equals(user.getPassword())) {
            response.put("token", "dummy-jwt-token");
        } else {
            response.put("message", "Invalid credentials");
        }
        return response;
    }
}
