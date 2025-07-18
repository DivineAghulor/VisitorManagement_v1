package com.divine.visitormanagement_v1.controller;

import com.divine.visitormanagement_v1.dto.AdminRegistrationRequest;
import com.divine.visitormanagement_v1.dto.ResidentRegistrationRequest;
import com.divine.visitormanagement_v1.dto.SecurityGuardRegistrationRequest;
import com.divine.visitormanagement_v1.model.Resident;
import com.divine.visitormanagement_v1.model.User;
import com.divine.visitormanagement_v1.service.ResidentService;
import com.divine.visitormanagement_v1.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for authentication and user registration endpoints.
 * Handles registration for Admin, Resident, and Security Guard.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserService userService;
    private final ResidentService residentService;

    /**
     * Public endpoint for initial admin registration.
     * Accessible without authentication.
     */
    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody AdminRegistrationRequest request) {
        userService.registerAdmin(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Admin registered successfully.");
    }

    /**
     * Endpoint for resident registration. Requires ADMIN role.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register/resident")
    public ResponseEntity<?> registerResident(@Valid @RequestBody ResidentRegistrationRequest request) {
        residentService.registerResident(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Resident registered successfully.");
    }

    /**
     * Endpoint for security guard registration. Requires ADMIN role.
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register/security-guard")
    public ResponseEntity<?> registerSecurityGuard(@Valid @RequestBody SecurityGuardRegistrationRequest request) {
        userService.registerSecurityGuard(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Security guard registered successfully.");
    }
}

// package com.divine.visitormanagement_v1.controller;

// import com.divine.visitormanagement_v1.dto.AdminRegistrationRequest;
// import com.divine.visitormanagement_v1.dto.ResidentRegistrationRequest;
// import com.divine.visitormanagement_v1.dto.SecurityGuardRegistrationRequest;
// import com.divine.visitormanagement_v1.model.Resident;
// import com.divine.visitormanagement_v1.model.User;
// import com.divine.visitormanagement_v1.service.ResidentService;
// import com.divine.visitormanagement_v1.service.UserService;
// import jakarta.validation.Valid;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.*;

// /**
//  * Controller for authentication and user registration endpoints.
//  * Handles registration for Admin, Resident, and Security Guard.
//  */
// @RestController
// @RequestMapping("/api/auth")
// @RequiredArgsConstructor
// @Slf4j
// public class AuthController {
//     private final UserService userService;
//     private final ResidentService residentService;

//     /**
//      * Public endpoint for initial admin registration.
//      * Accessible without authentication.
//      */
//     @PostMapping("/register/admin")
//     public ResponseEntity<?> registerAdmin(@Valid @RequestBody AdminRegistrationRequest request) {
//         try {
//             User user = userService.registerAdmin(request);
//             return ResponseEntity.status(HttpStatus.CREATED).body("Admin registered successfully.");
//         } catch (IllegalArgumentException e) {
//             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//         }
//     }

//     /**
//      * Endpoint for resident registration. Requires ADMIN role.
//      */
//     @PreAuthorize("hasRole('ADMIN')")
//     @PostMapping("/register/resident")
//     public ResponseEntity<?> registerResident(@Valid @RequestBody ResidentRegistrationRequest request) {
//         try {
//             Resident resident = residentService.registerResident(request);
//             return ResponseEntity.status(HttpStatus.CREATED).body("Resident registered successfully.");
//         } catch (IllegalArgumentException e) {
//             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//         }
//     }

//     /**
//      * Endpoint for security guard registration. Requires ADMIN role.
//      */
//     @PreAuthorize("hasRole('ADMIN')")
//     @PostMapping("/register/security-guard")
//     public ResponseEntity<?> registerSecurityGuard(@Valid @RequestBody SecurityGuardRegistrationRequest request) {
//         try {
//             User user = userService.registerSecurityGuard(request);
//             return ResponseEntity.status(HttpStatus.CREATED).body("Security guard registered successfully.");
//         } catch (IllegalArgumentException e) {
//             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//         }
//     }
// }




