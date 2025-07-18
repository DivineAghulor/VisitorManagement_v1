package com.divine.visitormanagement_v1.controller;

import com.divine.visitormanagement_v1.dto.VisitorCodeGenerationRequest;
import com.divine.visitormanagement_v1.dto.VisitorCodeVerificationRequest;
import com.divine.visitormanagement_v1.dto.VisitorCodeResponse;
import com.divine.visitormanagement_v1.model.Resident;
import com.divine.visitormanagement_v1.model.User;
import com.divine.visitormanagement_v1.model.VisitorCode;
import com.divine.visitormanagement_v1.repository.ResidentRepository;
import com.divine.visitormanagement_v1.repository.UserRepository;
import com.divine.visitormanagement_v1.service.VisitorCodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller for visitor code generation and verification endpoints.
 * Handles resident and security guard operations.
 */
@RestController
@RequestMapping("/api/visitors/codes")
@RequiredArgsConstructor
@Slf4j
public class VisitorCodeController {
    private final VisitorCodeService visitorCodeService;
    private final UserRepository userRepository;
    private final ResidentRepository residentRepository;

    /**
     * Endpoint for residents to generate a visitor code.
     * Requires RESIDENT role.
     */
    @PreAuthorize("hasRole('RESIDENT')")
    @PostMapping("/generate")
    public ResponseEntity<?> generateVisitorCode(@Valid @RequestBody VisitorCodeGenerationRequest request,
                                                 Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found.");
        }
        Resident resident = residentRepository.findAll().stream()
                .filter(r -> r.getUser().getId().equals(user.getId()))
                .findFirst().orElse(null);
        if (resident == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Resident profile not found.");
        }
        VisitorCode code = visitorCodeService.generateVisitorCode(resident, request);
        VisitorCodeResponse response = new VisitorCodeResponse();
        response.setId(code.getId());
        response.setCode(code.getCode());
        response.setVisitorName(code.getVisitorName());
        response.setResidentName(resident.getUser().getUsername());
        response.setHouseAddress(resident.getHouseAddress().getAddressLine1());
        response.setGeneratedAt(code.getGeneratedAt());
        response.setExpiresAt(code.getExpiresAt());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Endpoint for security guards to verify a visitor code.
     * Requires SECURITY_GUARD role.
     */
    @PreAuthorize("hasRole('SECURITY_GUARD')")
    @PostMapping("/verify")
    public ResponseEntity<?> verifyVisitorCode(@Valid @RequestBody VisitorCodeVerificationRequest request) {
        Optional<VisitorCode> codeOpt = visitorCodeService.verifyAndDeleteVisitorCode(request);
        if (codeOpt.isPresent()) {
            VisitorCode code = codeOpt.get();
            VisitorCodeResponse response = new VisitorCodeResponse();
            response.setId(code.getId());
            response.setCode(code.getCode());
            response.setVisitorName(code.getVisitorName());
            response.setResidentName(code.getResident().getUser().getUsername());
            response.setHouseAddress(code.getHouseAddress().getAddressLine1());
            response.setGeneratedAt(code.getGeneratedAt());
            response.setExpiresAt(code.getExpiresAt());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Visitor code not found or already used.");
        }
    }
}
