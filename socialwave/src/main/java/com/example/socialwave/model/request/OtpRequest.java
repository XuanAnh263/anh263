package com.example.socialwave.model.request;


import com.example.socialwave.statics.OtpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@ToString

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OtpRequest {

    UUID sessionId;

    @NotBlank(message = "Code is required")
    String code;

    @Future(message = "Expiry date must be in the future")
    LocalDateTime expiry;

    @NotNull(message = "Status is required")
    OtpStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Create is required")
            @PastOrPresent(message = "Creat date time must be in the past or present")
    LocalDateTime createDateTime;
}
