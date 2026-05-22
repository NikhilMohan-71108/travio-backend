package com.travio.travio_backend.application.domain.repository;

import com.travio.travio_backend.application.domain.entity.OtpVerification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpVerificationRepository  extends CrudRepository<OtpVerification, Long> {
    Optional<OtpVerification> findByEmailAndOtp(String email, String otp);
    Optional<OtpVerification> findTopByEmailOrderByCreatedAtDesc(String email);

}
