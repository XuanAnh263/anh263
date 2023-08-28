package com.example.goodread.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChallengeResponse {

    Long challengeId;

    Integer numberOfBook;

    Integer booksRead;

    LocalDate startedDate;

    LocalDate endDate;

}
