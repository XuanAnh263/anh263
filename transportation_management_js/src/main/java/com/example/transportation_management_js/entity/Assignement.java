package com.example.transportation_management_js.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Assignement {
    int id;
    Driver driver;
    Route route;
    Integer numberOfTurn;
    LocalDate dateOfAssignment;
}
