package com.example.demo_zalo_part1.model.request;

import com.example.demo_zalo_part1.entity.Conversation;
import com.example.demo_zalo_part1.entity.Sticker;
import com.example.demo_zalo_part1.entity.User;
import com.example.demo_zalo_part1.statics.Status;
import com.example.demo_zalo_part1.statics.Type;
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
public class MessageRequest {



    Conversation conversation;

    User senderId;

    Sticker sticker;
    Type type;
    String contentText;
    String contentRickText;
    Boolean mentioned;
    Boolean deleted;
    Boolean edited;
    Integer replyToMessageId;
    Status status;
    LocalDate createdAt;
    LocalDate updatedAt;
}
