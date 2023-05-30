package com.example.transportation_management_js.model.request;

import com.example.transportation_management_js.entity.Person;
import com.example.transportation_management_js.statics.DriverLevel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverCreateRequest extends Person {
    @NotNull(message = "Level cannot be null")
    DriverLevel driverLevel;

}
