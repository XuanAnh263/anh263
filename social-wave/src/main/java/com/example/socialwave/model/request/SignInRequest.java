package com.example.socialwave.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignInRequest {
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email existed")
    String email;

    @NotBlank(message = "User password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters ")
    String password;
}
