package com.example.transportation_management_js.entity;

import com.example.transportation_management_js.statics.DriverLevel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Driver extends Person {
    int id;
    DriverLevel driverLevel;

    public Driver(int id, String name, String address, String phone, DriverLevel driverLevel) {
        super(id, name, address, phone);
        this.driverLevel = driverLevel;
    }
}
