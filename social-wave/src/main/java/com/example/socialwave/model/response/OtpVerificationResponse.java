package com.example.socialwave.model.response;


import com.example.socialwave.entity.User;
import com.example.socialwave.statics.OtpStatus;

import java.time.LocalDate;

public class OtpVerificationResponse {

    User user;
    String otpCode;
    OtpStatus status;
    LocalDate createdAt;
    LocalDate updatedAt;
}
