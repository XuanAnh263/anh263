package com.example.transportation_management_js.model.request;

import com.example.transportation_management_js.entity.Person;
import com.example.transportation_management_js.statics.Level;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverCreateRequest {

    @Size(max = 100, message = "Name cannot over 100 characters")
    @NotBlank(message = "Driver name cannot be blank")
    String name;

    @Size(max = 255, message = "Address cannot over 255 characters")
    @NotBlank(message = "Student address cannot be blank")
    String address;

    @NotBlank(message = "Student phone cannot be blank")
    @Pattern(regexp = "\\d{10}", message = "Phone must be 10 number characters")
    String phone;

    Level level;
}