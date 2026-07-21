package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Repository for User - finds user by username for login
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
