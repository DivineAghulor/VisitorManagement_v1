package com.divine.visitormanagement_v1.repository;

import com.divine.visitormanagement_v1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository for User entity.
 * Provides CRUD operations and custom queries for users.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    // Find a user by username (for authentication)
    Optional<User> findByUsername(String username);
}
