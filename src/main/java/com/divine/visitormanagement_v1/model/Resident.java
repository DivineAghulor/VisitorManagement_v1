package com.divine.visitormanagement_v1.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Resident entity maps to the 'residents' table.
 * Links a user (with RESIDENT role) to a house address.
 * Multiple residents can share the same address.
 */
@Entity
@Table(name = "residents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Many-to-one relationship to User.
     * Each resident is a user.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Many-to-one relationship to HouseAddress.
     * Multiple residents can share the same address.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "house_address_id")
    private HouseAddress houseAddress;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * One-to-many relationship with VisitorCode.
     * A resident can generate multiple visitor codes.
     */
    @OneToMany(mappedBy = "resident")
    private Set<VisitorCode> visitorCodes;

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
