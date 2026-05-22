package com.travio.travio_backend.application.service;

import com.travio.travio_backend.application.domain.repository.OtpVerificationRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static jakarta.mail.Transport.send;

@Service
public class EmailService {

    private  final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendOtpEmail(String email,String otp){

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setFrom("nikhilmohan.11@gmail.com"); // add this
        message.setTo(email);
        message.setSubject("Travio OTP");
        message.setText("Your OTP is : " + otp);
        javaMailSender.send(message);

    }

}



