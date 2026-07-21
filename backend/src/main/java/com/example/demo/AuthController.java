package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// AuthController - handles login and registration APIs
@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // -------- POST /auth/login --------
    // Accepts: { "username": "admin", "password": "1234" }
    // Returns: { "success": true, "role": "ADMIN", "username": "admin" }
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> credentials) {

        Map<String, Object> response = new HashMap<>();

        String username = credentials.get("username");
        String password = credentials.get("password");

        // Find user in database by username
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            // Check if password matches (simple plain text check)
            if (user.getPassword().equals(password)) {
                response.put("success", true);
                response.put("role", user.getRole());
                response.put("username", user.getUsername());
                return response;
            }
        }

        // Login failed
        response.put("success", false);
        response.put("message", "Invalid username or password");
        return response;
    }

    // -------- POST /auth/register --------
    // Registers a new USER (not admin - admin is pre-created)
    // Accepts: { "username": "john", "password": "1234" }
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> data) {

        Map<String, Object> response = new HashMap<>();

        String username = data.get("username");
        String password = data.get("password");

        // Check if username already exists
        if (userRepository.findByUsername(username).isPresent()) {
            response.put("success", false);
            response.put("message", "Username already exists. Please choose another.");
            return response;
        }

        // Create new user with role USER
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setRole("USER");

        userRepository.save(newUser);

        response.put("success", true);
        response.put("message", "Registration successful! Please login.");
        return response;
    }
}
