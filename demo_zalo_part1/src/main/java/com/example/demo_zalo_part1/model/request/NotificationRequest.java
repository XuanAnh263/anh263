package com.example.demo_zalo_part1.model.request;

import com.example.demo_zalo_part1.statics.Status;
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
public class NotificationRequest {


    UserRequest user;
    String type;
    String content;
    Status status;
    Boolean read;
    LocalDate createdAt;
    LocalDate updatedAt;
}
