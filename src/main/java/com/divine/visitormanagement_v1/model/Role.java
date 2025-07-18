package com.divine.visitormanagement_v1.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

/**
 * Role entity maps to the 'roles' table.
 * Represents a user role (e.g., ADMIN, RESIDENT, SECURITY_GUARD).
 * Using a separate table allows for flexible role management and future expansion.
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    /**
     * Many-to-many relationship with User.
     * This is mapped by the 'roles' field in User.
     */
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
