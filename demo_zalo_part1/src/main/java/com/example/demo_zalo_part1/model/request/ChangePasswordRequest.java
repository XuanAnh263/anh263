package com.example.demo_zalo_part1.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChangePasswordRequest {
    @NotNull(message = "Old password í empty!")
    @NotEmpty(message = "Old password í empty!")
    @Size(min = 6, max = 20, message = "Password must be between 6 to 20 characters")
    @JsonProperty("old_password")
    String oldPassword;


    @NotNull(message = "New password í empty!")
    @NotEmpty(message = "New password í empty!")
    @Size(min = 6, max = 20, message = "Password must be between 6 to 20 characters")
    @JsonProperty("new_password")
    String newPassword;

}
