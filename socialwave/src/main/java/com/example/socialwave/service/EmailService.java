package com.example.socialwave.service;

import com.example.socialwave.entity.Otp;
import com.example.socialwave.entity.User;
import com.example.socialwave.repository.OtpRepository;
import com.example.socialwave.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@EnableAsync
@Service
public class EmailService {
    private final JavaMailSender javaMailSender;


    OtpService otpService;

    UserRepository userRepository;

    OtpRepository otpRepository;

    public EmailService(JavaMailSender javaMailSender, OtpService otpService, UserRepository userRepository, OtpRepository otpRepository) {
        this.javaMailSender = javaMailSender;
        this.otpService = otpService;
        this.userRepository = userRepository;
        this.otpRepository = otpRepository;
    }

    @Value("${spring.mail.username}")
    private String sender;

    public void sendOtpEmail(String email) {
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom(sender);
//        mailMessage.setTo(email);
//
//
//        mailMessage.setSubject("Reset Password OTP");
//        mailMessage.setText("Your OTP: " + generateOtp());
//        javaMailSender.send(mailMessage);


        SimpleMailMessage mailMessage = new SimpleMailMessage();


        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Otp otp = Otp.builder()
                    .code(otpService.generateOtp())
                    .creatDateTime(LocalDateTime.now())
                    .expiry(LocalDateTime.now().plus(30, ChronoUnit.MINUTES))
                    .build();
            otpRepository.save(otp);
            mailMessage.setFrom(sender);
            mailMessage.setTo(email);
            mailMessage.setText("Mã OTP của bạn là:" + otp.getCode() + ". Không chia sẻ mã này cho bất kỳ ai!");
            mailMessage.setSubject("[SkyHub] OTP Vefification");

            // Sending the mail
            javaMailSender.send(mailMessage);

        }
    }
}
