package com.example.socialwave.model.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    Long id;
    String email;

    String password;

    String username;

    LocalDate dob;

    String gender;

    String avatar;

    String phone;

    String address;

}
