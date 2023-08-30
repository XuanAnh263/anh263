package com.example.socialwave.model.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikeDTO {
    private Integer id;
    private  Integer peopleLikeId;
    private LocalDateTime updated;
    private Integer likePost;
}
