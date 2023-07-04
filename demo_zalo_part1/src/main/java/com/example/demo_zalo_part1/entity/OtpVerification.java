package com.example.demo_zalo_part1.entity;

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
@Entity
@Table(name = "otp_verifications")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OtpVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(targetEntity = User.class)
            @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    Otp otp;
    UUID session;
    Status status;
    Boolean success;
    LocalDateTime verificationTime;
    LocalDate createdAt;
    LocalDate updatedAt;
}
