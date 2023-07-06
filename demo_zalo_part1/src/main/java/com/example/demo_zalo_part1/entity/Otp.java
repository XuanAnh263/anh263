package com.example.demo_zalo_part1.entity;

import com.example.demo_zalo_part1.statics.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@ToString
@Entity
@Table(name = "otps")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "session_id")
    UUID sessionId;

    @Column(name = "code")
    String code;


    LocalDateTime expiry;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status;

    @CreatedDate
    LocalDateTime creatDateTime;
}
