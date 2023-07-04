package com.example.demo_zalo_part1.model.request;

import com.example.demo_zalo_part1.statics.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
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
    String code;
    LocalDateTime expiry;
    Status status;
    LocalDate createdAt;
}
