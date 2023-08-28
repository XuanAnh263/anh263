package com.example.goodread.entity;

import com.example.goodread.statics.ChallengeStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "challenges")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Challenge extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    Integer numberOfBook;

    @Enumerated(EnumType.STRING)
    ChallengeStatus challengeStatus;

    LocalDate startedDate;

    LocalDate endDate;

}
