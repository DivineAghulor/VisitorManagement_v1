package com.divine.visitormanagement_v1.repository;

import com.divine.visitormanagement_v1.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository for Role entity.
 * Provides CRUD operations and custom queries for roles.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Find a role by its name (e.g., "ADMIN")
    Optional<Role> findByName(String name);
}
