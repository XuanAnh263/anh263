package com.example.transportation_management_js.model.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@AllArgsConstructor
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
