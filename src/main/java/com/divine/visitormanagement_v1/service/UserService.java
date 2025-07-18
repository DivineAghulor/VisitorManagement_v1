package com.divine.visitormanagement_v1.service;

import com.divine.visitormanagement_v1.dto.AdminRegistrationRequest;
import com.divine.visitormanagement_v1.dto.ResidentRegistrationRequest;
import com.divine.visitormanagement_v1.dto.SecurityGuardRegistrationRequest;
import com.divine.visitormanagement_v1.model.Role;
import com.divine.visitormanagement_v1.model.User;
import com.divine.visitormanagement_v1.repository.RoleRepository;
import com.divine.visitormanagement_v1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

/**
 * Service for user registration and management.
 * Handles creation of Admin, Resident, and Security Guard users.
 * Ensures password hashing and role assignment.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Registers a new admin user.
     * Accessible publicly for initial setup.
     * @param request DTO containing username and password
     * @return created User
     */
    @Transactional
    public User registerAdmin(AdminRegistrationRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            log.warn("Attempt to register admin with existing username: {}", request.getUsername());
            throw new IllegalArgumentException("Username already exists.");
        }
        Role adminRole = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new IllegalStateException("ADMIN role not found."));
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Hash password
        user.setRoles(Collections.singleton(adminRole));
        userRepository.save(user);
        log.info("New admin registered: {}", user.getUsername());
        return user;
    }

    /**
     * Registers a new security guard user.
     * Requires ADMIN role (enforced at controller level).
     * @param request DTO containing username and password
     * @return created User
     */
    @Transactional
    public User registerSecurityGuard(SecurityGuardRegistrationRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            log.warn("Attempt to register security guard with existing username: {}", request.getUsername());
            throw new IllegalArgumentException("Username already exists.");
        }
        Role guardRole = roleRepository.findByName("SECURITY_GUARD")
                .orElseThrow(() -> new IllegalStateException("SECURITY_GUARD role not found."));
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Collections.singleton(guardRole));
        userRepository.save(user);
        log.info("New security guard registered: {}", user.getUsername());
        return user;
    }

    // Resident registration is handled in ResidentService to link with house address
}
