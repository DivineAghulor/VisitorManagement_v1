package com.divine.visitormanagement_v1.repository;

import com.divine.visitormanagement_v1.model.VisitorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository for VisitorCode entity.
 * Provides CRUD operations and custom queries for visitor codes.
 */
public interface VisitorCodeRepository extends JpaRepository<VisitorCode, Long> {
    // Find a visitor code by its unique code value
    Optional<VisitorCode> findByCode(String code);
}
