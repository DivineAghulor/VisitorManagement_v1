package com.divine.visitormanagement_v1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO for house address creation or update requests.
 * Includes validation for required fields.
 */
@Data
public class HouseAddressRequest {
    @NotBlank(message = "Address Line 1 is required.")
    private String addressLine1;

    private String addressLine2;

    @NotBlank(message = "City is required.")
    private String city;

    @NotBlank(message = "State is required.")
    private String state;

    @NotBlank(message = "Zip code is required.")
    private String zipCode;
}
