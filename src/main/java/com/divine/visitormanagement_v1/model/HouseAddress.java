package com.divine.visitormanagement_v1.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * HouseAddress entity maps to the 'house_addresses' table.
 * Represents a physical address that can be associated with multiple residents.
 */
@Entity
@Table(name = "house_addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HouseAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address_line1", nullable = false)
    private String addressLine1;

    @Column(name = "address_line2")
    private String addressLine2;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * One-to-many relationship with Resident.
     * Multiple residents can be linked to the same address.
     */
    @OneToMany(mappedBy = "houseAddress")
    private Set<Resident> residents;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
