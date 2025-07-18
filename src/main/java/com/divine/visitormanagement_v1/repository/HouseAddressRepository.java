package com.divine.visitormanagement_v1.repository;

import com.divine.visitormanagement_v1.model.HouseAddress;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for HouseAddress entity.
 * Provides CRUD operations for house addresses.
 */
public interface HouseAddressRepository extends JpaRepository<HouseAddress, Long> {
    // Additional custom queries can be added here as needed
}
