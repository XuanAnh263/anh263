package com.example.transportation_management_js.entity;

import com.example.transportation_management_js.statics.Level;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Driver extends Person {
    Level level;

    public Driver(int id, String name, String address, String phone, Level level) {
        super(id, name, address, phone);
        this.level = level;
    }

}
