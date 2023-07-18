package com.example.socialwave.model.response;

import com.example.socialwave.statics.OtpStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OtpResponse {
    String email;
    String code;
    OtpStatus status;
    UUID sessionId;
    LocalDateTime expiry;
}
