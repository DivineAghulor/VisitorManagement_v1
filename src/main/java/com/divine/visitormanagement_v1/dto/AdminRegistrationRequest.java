package com.divine.visitormanagement_v1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO for admin registration requests.
 * Used to register a new admin user.
 * Includes validation for username and password.
 */
@Data
public class AdminRegistrationRequest {
    @NotBlank(message = "Username is required.")
    @Size(min = 4, max = 100, message = "Username must be between 4 and 100 characters.")
    private String username;

    @NotBlank(message = "Password is required.")
    @Size(min = 8, max = 100, message = "Password must be at least 8 characters.")
    private String password;
}
