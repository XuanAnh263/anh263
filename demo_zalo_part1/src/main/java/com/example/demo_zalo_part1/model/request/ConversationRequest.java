package com.example.demo_zalo_part1.model.request;

import com.example.demo_zalo_part1.statics.Status;
import com.example.demo_zalo_part1.statics.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConversationRequest {
    @NotBlank(message = "Conversation name is required")
    String name;

    @NotBlank(message = "Conversation description is required")
    String description;

    @NotNull(message = "Conversation type is required")
    Type type;

    @NotNull(message = "Conversation status is required")
    Status status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            @NotNull(message = "Creat date time is required")
    LocalDateTime creatDateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Update is required")
    LocalDateTime lastModifiedDateTime;
}
