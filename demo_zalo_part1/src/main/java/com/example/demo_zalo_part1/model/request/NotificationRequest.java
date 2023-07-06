package com.example.demo_zalo_part1.model.request;

import com.example.demo_zalo_part1.statics.NotificationType;
import com.example.demo_zalo_part1.statics.Status;
import com.example.demo_zalo_part1.statics.Type;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@ToString

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationRequest extends BaseRequest {
    @NotNull
    UserRequest user;

    @NotNull
    NotificationType type;

    @NotBlank
            @Size(max = 1000, message = "Content must not exceed 1000 characters")
    String content;

    @NotNull
    NotificationType status;

    @AssertTrue
    Boolean read;

}
