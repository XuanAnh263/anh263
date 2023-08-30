package com.example.socialwave.model.dto;



import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Integer id;

    private String content;

    private String media;

    private LocalDateTime updated;
    private LocalDateTime created;
    private Integer author;

    private Integer numberOfLikes;

    private Integer numberOfComments;

//    private String isLiked;
//
//    private String isBlock;
//
//    private String canEdit;
//
//    private String canComment;

}
