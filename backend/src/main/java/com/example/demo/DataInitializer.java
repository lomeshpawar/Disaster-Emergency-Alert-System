package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// This class runs automatically when backend starts
// It creates default admin and user accounts if they don't exist
@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Create default ADMIN account if not exists
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin123");
            admin.setRole("ADMIN");
            userRepository.save(admin);
            System.out.println("✅ Default ADMIN account created → username: admin | password: admin123");
        }

        // Create default USER account if not exists
        if (userRepository.findByUsername("user").isEmpty()) {
            User user = new User();
            user.setUsername("user");
            user.setPassword("user123");
            user.setRole("USER");
            userRepository.save(user);
            System.out.println("✅ Default USER account created → username: user | password: user123");
        }
    }
}
