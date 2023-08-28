package com.example.goodread.model.response;

import com.example.goodread.entity.Otp;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OtpVerificationResponse {
    Otp otp;

    boolean success;

    Integer timesVerification;

}
