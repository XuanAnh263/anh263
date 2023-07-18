package com.example.socialwave.service;


import com.example.socialwave.entity.Otp;
import com.example.socialwave.entity.User;
import com.example.socialwave.repository.OtpRepository;
import com.example.socialwave.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Value("${spring.mail.username}")
    private String sender;

    public void sendOtpEmail(String email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(email);
        mailMessage.setSubject("Reset Password OTP");
        mailMessage.setText("Your OTP: " + generateOtp());
        javaMailSender.send(mailMessage);

    }

    public Otp generateOtp() {
        Random random = new Random();

        int otpNumber = random.nextInt(900000) + 100000;
        String code = String.valueOf(otpNumber);

        Otp otp = new Otp();
        otp.setCode(code);
        otp.setSessionId(UUID.randomUUID());
        otp.setExpiry(LocalDateTime.now().plusMinutes(15));
        otp.setCreatDateTime(LocalDateTime.now());
        return otpRepository.save(otp);
    }



}
