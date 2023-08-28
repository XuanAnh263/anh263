package com.example.goodread.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Long id;

    String email;

    boolean activated;

    boolean locked;

    String roles;

    String avatar;

    String fullName;

    String gender;

    LocalDate dob;

    String phone;

    String about;

    String wardFullName;

    String districtFullName;

    String provinceFullName;

    String ward;

    String district;

    String province;

    String street;
}
