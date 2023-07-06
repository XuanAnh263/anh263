package com.example.demo_zalo_part1.model.request;

import com.example.demo_zalo_part1.statics.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@ToString

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OtpVerification extends BaseRequest {


    UserRequest user;
    OtpRequest otp;
    UUID session;
    Status status;

    @NotNull(message = "success is required")
    Boolean success;


    LocalDateTime verificationTime;

}
