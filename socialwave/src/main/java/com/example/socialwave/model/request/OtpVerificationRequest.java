package com.example.socialwave.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OtpVerificationRequest extends BaseRequest {
    String email;
    String code;
    UUID sessionId;

}
