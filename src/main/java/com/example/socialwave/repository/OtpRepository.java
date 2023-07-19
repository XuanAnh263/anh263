package com.example.socialwave.repository;


import com.example.socialwave.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> findByCode (String code);
     Otp findBySessionId(UUID sessionId);
}
