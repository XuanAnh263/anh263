package com.example.goodread.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReadingBookRequest extends BaseSearchRequest {

    Long userId;

    Long bookId;

    String readingStatus;

    double readingProgress;

    LocalDate startedDate;

    LocalDate finishedDate;

}
