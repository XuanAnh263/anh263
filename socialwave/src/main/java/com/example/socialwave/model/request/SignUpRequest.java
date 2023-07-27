package com.example.socialwave.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
//@PasswordValueMatch.List({
//        @PasswordValueMatch(
//                field = "password",
//                fieldMatch = "confirmPassword",
//                message = "Passwords do not match!"
//        )
//})
//TODO valid pass and confrimpass
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpRequest {
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email existed")
    String email;

    @NotBlank(message = "User password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters ")
    String password;

    @NotBlank(message = "User password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters ")
    String confirmPassword;

    @NotBlank(message = "Username is required")
    @Length(max = 50, message = "FullName cannot exceed 50 characters")
    String username;

    @NotBlank(message = "Phone user cannot be blank")
    @Pattern(regexp = "\\d{10}", message = "Phone must be 10 number characters")
    String phone;
}
