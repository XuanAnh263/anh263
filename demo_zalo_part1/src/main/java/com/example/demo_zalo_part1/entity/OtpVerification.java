package com.example.demo_zalo_part1.entity;

import com.example.demo_zalo_part1.statics.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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

    @ManyToOne(targetEntity = Otp.class)
            @JoinColumn(name = "otp_id")
    Otp otp;

    @Column(name = "session")
    UUID session;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status;

    @Column(name = "success")
    Boolean success;

    @Column(name = "verification_time")
    LocalDateTime verificationTime;

    @CreatedDate
    LocalDateTime createDateTime;

    @LastModifiedDate
    LocalDateTime lastModifiedDateTime;
}
