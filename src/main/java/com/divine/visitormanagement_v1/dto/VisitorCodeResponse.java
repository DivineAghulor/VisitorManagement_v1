package com.divine.visitormanagement_v1.dto;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * DTO for returning visitor code data in API responses.
 */
@Data
public class VisitorCodeResponse {
    private Long id;
    private String code;
    private String visitorName;
    private String residentName;
    private String houseAddress;
    private LocalDateTime generatedAt;
    private LocalDateTime expiresAt;
}
