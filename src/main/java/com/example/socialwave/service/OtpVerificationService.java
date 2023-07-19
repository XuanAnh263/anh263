package com.example.socialwave.service;

import com.example.socialwave.entity.Otp;
import com.example.socialwave.entity.OtpVerification;
import com.example.socialwave.entity.User;
import com.example.socialwave.model.request.ResetPasswordRequest;
import com.example.socialwave.model.response.OtpVerificationResponse;
import com.example.socialwave.repository.OtpRepository;
import com.example.socialwave.repository.OtpVerificationRepository;
import com.example.socialwave.repository.UserRepository;
import com.example.socialwave.statics.OtpStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OtpVerificationService {
    OtpVerificationRepository otpVerificationRepository;
    OtpRepository otpRepository;
    UserRepository userRepository;
    public boolean verifyOtp(ResetPasswordRequest request) {
        Otp otp = otpRepository.findBySessionId(request.getSessionId());
        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if (otp != null && otp.getCode().equals(request.getCode()) && otp.getExpiry().isAfter(LocalDateTime.now())) {
                OtpVerification otpVerification = new OtpVerification();

                otpVerification.setUserId(user.get());
                otpVerification.setOtp(otp);
                otpVerification.setSession(request.getSessionId());
                otpVerification.setStatus(OtpStatus.VERIFIED);
                otpVerification.setSuccess(true);
                otpVerification.setVerificationTime(LocalDateTime.now());
                otpVerification.setCreateDateTime(LocalDateTime.now());
                otpVerification.setLastModifiedDateTime(LocalDateTime.now());

                otpVerificationRepository.save(otpVerification);
                return true;
            }

        return false;


    }


}
