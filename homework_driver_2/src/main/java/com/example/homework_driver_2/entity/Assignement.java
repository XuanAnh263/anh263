package com.example.homework_driver_2.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Assignement {
    int id;
    Driver driver;
    Route route;
    int numberOfTurn;
    LocalDate dateOfAssignment;

}
