package com.divine.visitormanagement_v1.service;

import com.divine.visitormanagement_v1.dto.VisitorCodeGenerationRequest;
import com.divine.visitormanagement_v1.dto.VisitorCodeVerificationRequest;
import com.divine.visitormanagement_v1.model.HouseAddress;
import com.divine.visitormanagement_v1.model.Resident;
import com.divine.visitormanagement_v1.model.VisitorCode;
import com.divine.visitormanagement_v1.repository.VisitorCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

/**
 * Service for visitor code generation, verification, and cleanup.
 * Handles resident code generation, security guard verification, and daily cleanup.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class VisitorCodeService {
    private final VisitorCodeRepository visitorCodeRepository;

    /**
     * Generates a unique visitor code for a resident.
     * @param resident Resident generating the code
     * @param request DTO containing visitor name
     * @return created VisitorCode
     */
    @Transactional
    public VisitorCode generateVisitorCode(Resident resident, VisitorCodeGenerationRequest request) {
        String code = generateUniqueCode();
        VisitorCode visitorCode = new VisitorCode();
        visitorCode.setCode(code);
        visitorCode.setVisitorName(request.getVisitorName());
        visitorCode.setResident(resident);
        visitorCode.setHouseAddress(resident.getHouseAddress());
        visitorCode.setExpiresAt(LocalDateTime.now().withHour(23).withMinute(59).withSecond(59)); // Expires end of day
        visitorCodeRepository.save(visitorCode);
        log.info("Visitor code generated for {} by resident {}", request.getVisitorName(), resident.getUser().getUsername());
        return visitorCode;
    }

    /**
     * Verifies a visitor code and deletes it upon success.
     * @param request DTO containing code to verify
     * @return VisitorCode details if found and deleted
     */
    @Transactional
    public Optional<VisitorCode> verifyAndDeleteVisitorCode(VisitorCodeVerificationRequest request) {
        Optional<VisitorCode> codeOpt = visitorCodeRepository.findByCode(request.getCode());
        if (codeOpt.isPresent()) {
            visitorCodeRepository.delete(codeOpt.get());
            log.info("Visitor code {} verified and deleted.", request.getCode());
        } else {
            log.warn("Attempt to verify non-existent visitor code: {}", request.getCode());
        }
        return codeOpt;
    }

    /**
     * Scheduled task to delete all unused (unverified) visitor codes at the end of each day.
     * Runs at 23:59 every day.
     */
    @Scheduled(cron = "0 59 23 * * *")
    @Transactional
    public void cleanupExpiredVisitorCodes() {
        LocalDateTime now = LocalDateTime.now();
        visitorCodeRepository.findAll().stream()
            .filter(code -> code.getExpiresAt().isBefore(now))
            .forEach(code -> {
                visitorCodeRepository.delete(code);
                log.info("Expired visitor code {} deleted during daily cleanup.", code.getCode());
            });
    }

    /**
     * Generates a short unique alphanumeric code.
     * @return unique code string
     */
    private String generateUniqueCode() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        String code;
        do {
            code = random.ints(6, 0, chars.length())
                    .mapToObj(i -> String.valueOf(chars.charAt(i)))
                    .reduce("", String::concat);
        } while (visitorCodeRepository.findByCode(code).isPresent());
        return code;
    }
}
