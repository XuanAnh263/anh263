package com.example.homework_driver_2.model.respond;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RouteResponse {
    int id;
    String name;
    double distance;
    int numberOfStop;
}
