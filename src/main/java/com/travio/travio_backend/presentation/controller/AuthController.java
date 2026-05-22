package com.travio.travio_backend.presentation.controller;


import com.travio.travio_backend.application.dto.request.LoginRequest;
import com.travio.travio_backend.application.dto.request.RegisterRequest;
import com.travio.travio_backend.application.dto.response.LoginResponse;
import com.travio.travio_backend.application.dto.response.UserResponse;
import com.travio.travio_backend.application.service.OtpService;
import com.travio.travio_backend.application.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins="*")
public class AuthController {
 private final UserService userService;
 private  final OtpService otpService;
    public AuthController(UserService userService, OtpService otpService) {
        this.userService = userService;
        this.otpService = otpService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(
            @RequestBody RegisterRequest request) {
            UserResponse response =  userService.registerUser(request);
            return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser( @RequestBody LoginRequest request) {
        LoginResponse response =userService.loginUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/otp-create")
    public String createOtp(@RequestParam String email) {
         otpService.generateAndSendOtp(email);
         return "OTP sent successfully";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String email , @RequestParam String otp) {
        boolean isValid =
                otpService.verifyOtp(email, otp);

        if(isValid){
            return "OTP verified successfully";
        }

        return "Invalid OTP";
    }
}
