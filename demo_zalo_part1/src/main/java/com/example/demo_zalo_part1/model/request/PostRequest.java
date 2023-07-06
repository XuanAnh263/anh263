package com.example.demo_zalo_part1.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostRequest extends BaseRequest{


    UserRequest user;
    String caption;
    String contentMedia;
    Long likeCount;
    Long commentCount;

}
