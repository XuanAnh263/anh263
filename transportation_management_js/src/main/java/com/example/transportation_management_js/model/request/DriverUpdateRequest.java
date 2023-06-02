package com.example.transportation_management_js.model.request;

import com.example.transportation_management_js.entity.Person;
import com.example.transportation_management_js.statics.Level;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverUpdateRequest extends Person {
    int id;
    String name;
    String address;
    String phone;
    Level level;

}
