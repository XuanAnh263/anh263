package com.example.transportation_management_js.model.response;

import com.example.transportation_management_js.entity.Driver;
import com.example.transportation_management_js.entity.Route;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AssigmentDriverResponse {
    int id;
    Driver driver;
    Route route;
    int numberOfTurns;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate date;
}
