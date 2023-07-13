package com.example.demo_zalo_part1.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email existed")
    String email;

    @NotBlank(message = "User password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters ")
    String password;

    @NotBlank(message = "Username is required")
    @Length(max = 50, message = "FullName cannot exceed 50 characters")
    String username;
}
