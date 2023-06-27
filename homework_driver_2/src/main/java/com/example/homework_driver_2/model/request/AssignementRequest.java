package com.example.homework_driver_2.model.request;


import com.example.homework_driver_2.entity.Driver;
import com.example.homework_driver_2.entity.Route;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class AssignementRequest {

    int id;

    int driverId;
    String driverName;

    int routeId;
    String routeName;

    int numberOfTurn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate dateOfAssignment;

}
