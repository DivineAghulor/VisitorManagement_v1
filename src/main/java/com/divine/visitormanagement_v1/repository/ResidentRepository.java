package com.divine.visitormanagement_v1.repository;

import com.divine.visitormanagement_v1.model.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for Resident entity.
 * Provides CRUD operations for residents.
 */
public interface ResidentRepository extends JpaRepository<Resident, Long> {
    // Additional custom queries can be added here as needed
}
