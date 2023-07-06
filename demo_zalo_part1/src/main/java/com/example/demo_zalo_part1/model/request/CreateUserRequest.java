package com.example.demo_zalo_part1.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {

    @Email(message = "Invalid email format")
            @NotBlank(message = "Email existed")
    String email;

    @NotBlank(message = "User password cannot be blank")
            @Size(min = 6, message = "Password must be at least 6 characters ")
    String password;

    @NotBlank(message = "Username is required")
            @Length(max = 50, message = "FullName cannot exceed 50 characters")
    String username;

    @JsonFormat(pattern = "yyyy-MM-dd")
            @NotNull(message = "Date cannot be blank")
            @PastOrPresent(message = "Date should be less than current date")
    LocalDate dob;

    @NotBlank(message = "Gender user cannot be blank")
    String gender;

    @NotBlank(message = "Avatar user cannot be blank")
    String avatar;

    @NotBlank(message = "Phone user cannot be blank")
            @Pattern(regexp = "\\d{10}", message = "Phone must be 10 number characters")
    String phone;

    @Size(max = 255, message = "Address cannot over 255 characters")
            @NotBlank(message = "User address cannot be blank")
    String address;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime deletedAt;

}
