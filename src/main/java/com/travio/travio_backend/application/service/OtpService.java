package com.travio.travio_backend.application.service;

import com.travio.travio_backend.application.domain.entity.OtpVerification;
import com.travio.travio_backend.application.domain.repository.OtpVerificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class OtpService {
    private final OtpVerificationRepository otpVerificationRepository;
    private final  EmailService emailService;
    public OtpService(OtpVerificationRepository otpVerificationRepository, EmailService emailService) {
        this.otpVerificationRepository = otpVerificationRepository;
        this.emailService = emailService;
    }

    public void generateAndSendOtp(String email) {
        String otp = generateOtp();
        saveOtp(email, otp);
        emailService.sendOtpEmail(email, otp);
    }

     public String generateOtp(){
        Random random = new Random();
        int otp =  100000 + random.nextInt(900000);
        return  String.valueOf(otp);
     }

     public void saveOtp(String email, String otp){

         OtpVerification otpEntity = new OtpVerification();
         otpEntity.setEmail(email);
         otpEntity.setOtp(otp);
         otpEntity.setCreatedAt(LocalDateTime.now());
         otpEntity.setExpiresAt(LocalDateTime.now().plusMinutes(5));
         otpEntity.setVerified(false);
         otpVerificationRepository.save(otpEntity);
     }

     public  boolean verifyOtp(String email, String otp){
         Optional<OtpVerification> record
                 =otpVerificationRepository.findByEmailAndOtp(email, otp);

            if(record.isEmpty()){
                 return false;
            }

         OtpVerification otpData = record.get();
         if (otpData.getExpiresAt().isBefore(LocalDateTime.now())) {
             return false;
         }
         if (otpData.isVerified() || !otpData.getOtp().equals(otp)) {
             return false;
         }

         otpData.setVerified(true);
         otpVerificationRepository.save(otpData);

         return true;

     }
}
