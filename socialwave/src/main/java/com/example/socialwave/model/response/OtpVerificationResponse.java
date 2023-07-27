package com.example.socialwave.model.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OtpVerificationResponse {
    String code;
    LocalDateTime expiry;
    UUID sessionId;
}
