package com.example.demo_zalo_part1.model.request;

import com.example.demo_zalo_part1.entity.Conversation;
import com.example.demo_zalo_part1.entity.Sticker;
import com.example.demo_zalo_part1.entity.User;
import com.example.demo_zalo_part1.statics.Status;
import com.example.demo_zalo_part1.statics.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@ToString

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageRequest {

    Conversation conversation;

    User senderId;

    Sticker sticker;

    @NotNull
    Type type;

    @NotBlank(message = "Content text is required")
            @Size(max = 1000, message = "Content text must not exceed 1000 characters")
    String contentText;

    @NotBlank(message = "Content rick text is required")
    @Size(max = 5000, message = "Content rick text must not exceed 5000 characters")
    String contentRickText;

    Boolean mentioned;

    Boolean deleted;

    Boolean edited;

    Long replyToMessageId;

    @NotNull
    Status status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Creat date time is required")
    LocalDateTime createdDateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Update is required")
    LocalDateTime lastModifiedDateTime;
}
