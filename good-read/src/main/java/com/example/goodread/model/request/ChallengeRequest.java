package com.example.goodread.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChallengeRequest extends BaseSearchRequest {

    @NotNull
    @Min(value = 1)
    Integer numberOfBook;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate startedDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;
}
