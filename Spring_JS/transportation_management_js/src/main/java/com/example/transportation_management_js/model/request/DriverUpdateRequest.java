package com.example.transportation_management_js.model.request;

import com.example.transportation_management_js.entity.Person;
import com.example.transportation_management_js.statics.DriverLevel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverUpdateRequest extends Person {
    int id;

    @NotBlank(message = "Level cannot be null")
    DriverLevel driverLevel;

}
