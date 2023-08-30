package com.example.socialwave.model.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private Integer id;


    private  Integer user;


    private String content;


    private LocalDateTime updated;
    private LocalDateTime created;

    private Integer post;
}
