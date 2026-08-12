package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // POST /auth/login
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> credentials) {
        Map<String, Object> response = new HashMap<>();

        String username = credentials.get("username");
        String password = credentials.get("password");

        if (username == null || password == null) {
            response.put("success", false);
            response.put("message", "Username and password are required");
            return response;
        }

        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
            User user = userOpt.get();
            response.put("success", true);
            response.put("role", user.getRole());
            response.put("username", user.getUsername());
            return response;
        }

        response.put("success", false);
        response.put("message", "Invalid username or password");
        return response;
    }

    // POST /auth/register
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> data) {
        Map<String, Object> response = new HashMap<>();

        String username = data.get("username");
        String password = data.get("password");

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            response.put("success", false);
            response.put("message", "Username and password are required");
            return response;
        }

        if (userRepository.findByUsername(username).isPresent()) {
            response.put("success", false);
            response.put("message", "Username already exists. Please choose another.");
            return response;
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRole("USER");

        userRepository.save(newUser);

        response.put("success", true);
        response.put("message", "Registration successful! Please login.");
        return response;
    }
}
