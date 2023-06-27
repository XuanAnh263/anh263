package com.example.homework_driver_2.model.respond;


import com.example.homework_driver_2.entity.Driver;
import com.example.homework_driver_2.entity.Route;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AssignementResponse {
    int id;

    int driverId;
    String driverName;

    int routeId;
    String routeName;

    int numberOfTurn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate dateOfAssignment;

}
