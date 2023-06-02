package com.example.transportation_management_js.model.response;

import com.example.transportation_management_js.entity.Person;
import com.example.transportation_management_js.statics.Level;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverResponse extends Person {
    int id;
    String name;
    String address;
    String phone;
    Level level;

}
