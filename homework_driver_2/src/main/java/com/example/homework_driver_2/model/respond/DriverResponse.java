package com.example.homework_driver_2.model.respond;

import com.example.homework_driver_2.statics.Level;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverResponse {
    int id;

    String name;

    String address;

    String phone;

    Level level;
}
