package com.divine.visitormanagement_v1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO for visitor code verification requests.
 * Used by security guards to verify a visitor code.
 * Includes validation for code field.
 */
@Data
public class VisitorCodeVerificationRequest {
    @NotBlank(message = "Visitor code is required.")
    private String code;
}
