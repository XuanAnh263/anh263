package com.example.transportation_management_js.model.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AssignmentSheetUpdateRequest {
    int id;

    Integer driverId;
    DriverUpdateRequest driver;

    RouteUpdateRequest route;

    @NotNull(message = "Number Of Turns cannot be blank")
    @Range(min = 1, max = 15, message = "Number of turns form 1 to 15")
    int numberOfTurns;

    @NotNull(message = "Date cannot be blank")
    @PastOrPresent(message = "Date should be less than current date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate date;
}
