package com.divine.visitormanagement_v1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO for resident registration requests.
 * Used to register a new resident user and their house address.
 * Includes validation for all fields.
 */
@Data
public class ResidentRegistrationRequest {
    @NotBlank(message = "Username is required.")
    @Size(min = 4, max = 100, message = "Username must be between 4 and 100 characters.")
    private String username;

    @NotBlank(message = "Password is required.")
    @Size(min = 8, max = 100, message = "Password must be at least 8 characters.")
    private String password;

    @NotBlank(message = "Address Line 1 is required.")
    private String addressLine1;

    private String addressLine2;

    @NotBlank(message = "City is required.")
    private String city;

    @NotBlank(message = "State is required.")
    private String state;

    @NotBlank(message = "Zip code is required.")
    @Size(min = 4, max = 20, message = "Zip code must be between 4 and 20 characters.")
    private String zipCode;
}
