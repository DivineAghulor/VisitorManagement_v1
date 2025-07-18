package com.divine.visitormanagement_v1.service;

import com.divine.visitormanagement_v1.dto.HouseAddressRequest;
import com.divine.visitormanagement_v1.dto.ResidentRegistrationRequest;
import com.divine.visitormanagement_v1.model.HouseAddress;
import com.divine.visitormanagement_v1.model.Resident;
import com.divine.visitormanagement_v1.model.Role;
import com.divine.visitormanagement_v1.model.User;
import com.divine.visitormanagement_v1.repository.ResidentRepository;
import com.divine.visitormanagement_v1.repository.RoleRepository;
import com.divine.visitormanagement_v1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

/**
 * Service for resident registration and management.
 * Handles creation of resident users and links them to house addresses.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ResidentService {
    private final ResidentRepository residentRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final HouseAddressService houseAddressService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Registers a new resident user and links to a house address.
     * Requires ADMIN role (enforced at controller level).
     * @param request DTO containing user and address details
     * @return created Resident
     */
    @Transactional
    public Resident registerResident(ResidentRegistrationRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            log.warn("Attempt to register resident with existing username: {}", request.getUsername());
            throw new IllegalArgumentException("Username already exists.");
        }
        Role residentRole = roleRepository.findByName("RESIDENT")
                .orElseThrow(() -> new IllegalStateException("RESIDENT role not found."));
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Collections.singleton(residentRole));
        userRepository.save(user);
        HouseAddressRequest addressRequest = new HouseAddressRequest();
        addressRequest.setAddressLine1(request.getAddressLine1());
        addressRequest.setAddressLine2(request.getAddressLine2());
        addressRequest.setCity(request.getCity());
        addressRequest.setState(request.getState());
        addressRequest.setZipCode(request.getZipCode());
        HouseAddress address = houseAddressService.createAddress(addressRequest);
        Resident resident = new Resident();
        resident.setUser(user);
        resident.setHouseAddress(address);
        residentRepository.save(resident);
        log.info("New resident registered: {} at address {}", user.getUsername(), address.getAddressLine1());
        return resident;
    }
}
