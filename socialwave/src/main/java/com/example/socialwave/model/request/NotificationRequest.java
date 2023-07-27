package com.example.socialwave.model.request;


import com.example.socialwave.statics.NotificationStatus;
import com.example.socialwave.statics.NotificationType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


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
    NotificationStatus status;

    @AssertTrue
    Boolean read;

}
