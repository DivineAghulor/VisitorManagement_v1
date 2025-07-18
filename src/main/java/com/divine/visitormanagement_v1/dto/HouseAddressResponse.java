package com.divine.visitormanagement_v1.dto;

import lombok.Data;

/**
 * DTO for returning house address data in API responses.
 */
@Data
public class HouseAddressResponse {
    private Long id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;
}
