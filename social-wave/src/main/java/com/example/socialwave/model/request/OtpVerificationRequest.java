package com.example.socialwave.model.request;

import com.example.socialwave.entity.Otp;
import com.example.socialwave.entity.User;
import com.example.socialwave.statics.OtpStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;


@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OtpVerificationRequest extends BaseRequest {


    User user;
    Otp otp;
    UUID session;
    OtpStatus status;

    @NotNull(message = "success is required")
    Boolean success;


    LocalDateTime verificationTime;

}
