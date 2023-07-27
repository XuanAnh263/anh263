package com.example.socialwave.service;


import com.example.socialwave.entity.Otp;
import com.example.socialwave.entity.User;
import com.example.socialwave.model.response.OtpVerificationResponse;
import com.example.socialwave.repository.OtpRepository;
import com.example.socialwave.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service

public class OtpService {
    @Autowired
    private final OtpRepository otpRepository;
    @Autowired
    private final JavaMailSender javaMailSender;
    @Autowired
    private final UserRepository userRepository;

    public OtpService(OtpRepository otpRepository, JavaMailSender javaMailSender, UserRepository userRepository) {
        this.otpRepository = otpRepository;
        this.javaMailSender = javaMailSender;
        this.userRepository = userRepository;
    }



//    public Otp generateOtp() {
////        Random random = new Random();
////
////        int otpNumber = random.nextInt(900000) + 100000;
////        String code = String.valueOf(otpNumber);
////
////        Otp otp = new Otp();
////        otp.setCode(code);
////        otp.setSessionId(UUID.randomUUID());
////        otp.setExpiry(LocalDateTime.now().plusMinutes(15));
////        otp.setCreatDateTime(LocalDateTime.now());
////        return otpRepository.save(otp);
//
//    }
    public String generateOtp() {
    StringBuilder otp = new StringBuilder();

    String OTP_CHARACTERS = "0123456789QWERTYUIOPASDFGHJKLZXCVBNM";

    Random random = new Random();
    for (int i = 0; i < 6; i++) {
        int index = random.nextInt(OTP_CHARACTERS.length());
        otp.append(OTP_CHARACTERS.charAt(index));
    }

    return otp.toString();
    }

    public OtpVerificationResponse verifyOtp(String code) {
        Optional<Otp> optional=otpRepository.findByCode(code);
        if (optional.isPresent()){
            Otp otp=optional.get();
            return OtpVerificationResponse.builder()
                    .code(otp.getCode())
                    .expiry(LocalDateTime.now().plusMinutes(15))
                    .sessionId(UUID.randomUUID())
                    .build();
        }
        return null;
    }

}
