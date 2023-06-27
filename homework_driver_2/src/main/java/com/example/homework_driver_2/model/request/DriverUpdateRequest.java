package com.example.homework_driver_2.model.request;

import com.example.homework_driver_2.statics.Level;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverUpdateRequest {
    int id;

    //    @Size(max = 100, message = "Name cannot over 100 characters")
//    @NotBlank(message = "Driver name cannot be blank")
    String name;

    //    @Size(max = 255, message = "Address cannot over 255 characters")
//    @NotBlank(message = "Student address cannot be blank")
    String address;

    //    @NotBlank(message = "Student phone cannot be blank")
//    @Pattern(regexp = "\\d{10}", message = "Phone must be 10 number characters")
    String phone;
    Level level;

}
