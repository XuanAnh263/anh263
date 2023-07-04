package com.example.demo_zalo_part1.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

    String email;
    String password;
    String username;
    LocalDate dob;
    String gender;
    String avatar;
    String phone;
    String address;

    LocalDate deletedAt;
}
