package com.example.transportation_management_js.model.request;

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
@FieldDefaults(level = AccessLevel.PROTECTED )
public class PersonUpdateRequest {
    int id;
    @Size(max = 100, message = "Name cannot over 100 characters")
    @NotBlank(message = "Name cannot be blank")
    String name;

    @Size(max = 255, message = "Address cannot over 255 characters")
    @NotBlank(message = "Address cannot be blank")
    String address;

    @NotBlank(message = "Phone cannot be blank")
    @Pattern(regexp = "\\d{10}", message = "Phone must be 10 number characters")
    String phone;
}
