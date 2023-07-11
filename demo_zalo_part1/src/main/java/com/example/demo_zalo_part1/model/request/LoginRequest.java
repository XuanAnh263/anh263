package com.example.demo_zalo_part1.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {

    @NotBlank
    @Size(max = 50)
    String email;

    @NotBlank
    @Size(min = 6, message = "Password must be at least 6 characters ")
    String password;

}
