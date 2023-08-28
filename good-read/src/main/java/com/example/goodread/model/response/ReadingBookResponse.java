package com.example.goodread.model.response;

import com.example.goodread.entity.Book;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReadingBookResponse{

    Book book;

    String readingStatus;

    double readingProgress;

    LocalDate addedDateTime;

    LocalDate startedDateTime;

    LocalDate finishedDateTime;

}
