package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

// Creates optional initial accounts from environment variables.
@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        createUserFromEnvironment("ADMIN_USERNAME", "ADMIN_PASSWORD", "ADMIN");
        createUserFromEnvironment("USER_USERNAME", "USER_PASSWORD", "USER");
    }

    private void createUserFromEnvironment(String usernameKey, String passwordKey, String role) {
        String username = System.getenv(usernameKey);
        String password = System.getenv(passwordKey);

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return;
        }

        if (userRepository.findByUsername(username).isEmpty()) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(role);
            userRepository.save(user);
        }
    }
}
