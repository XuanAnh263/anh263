package com.example.goodread.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationRequest {
    @Size(max = 100, message = "Name cannot over 100 characters")
    String fullName;

    @NotBlank
    @Email
    String email;

    @NotBlank
    String password;

}
