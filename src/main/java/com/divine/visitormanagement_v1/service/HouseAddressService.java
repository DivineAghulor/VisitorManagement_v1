package com.divine.visitormanagement_v1.service;

import com.divine.visitormanagement_v1.dto.HouseAddressRequest;
import com.divine.visitormanagement_v1.model.HouseAddress;
import com.divine.visitormanagement_v1.repository.HouseAddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for house address management.
 * Handles creation and retrieval of house addresses.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class HouseAddressService {
    private final HouseAddressRepository houseAddressRepository;

    /**
     * Creates a new house address from the request DTO.
     * @param request DTO containing address details
     * @return created HouseAddress
     */
    @Transactional
    public HouseAddress createAddress(HouseAddressRequest request) {
        HouseAddress address = new HouseAddress();
        address.setAddressLine1(request.getAddressLine1());
        address.setAddressLine2(request.getAddressLine2());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setZipCode(request.getZipCode());
        houseAddressRepository.save(address);
        log.info("New house address created: {}", address.getAddressLine1());
        return address;
    }

    // Additional methods for address lookup and management can be added here
}
