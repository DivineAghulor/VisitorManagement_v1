package com.divine.visitormanagement_v1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO for visitor code generation requests.
 * Used by residents to generate a code for a visitor.
 * Includes validation for visitor name.
 */
@Data
public class VisitorCodeGenerationRequest {
    @NotBlank(message = "Visitor name is required.")
    private String visitorName;
}
