package com.example.socialwave.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateLikeRequest {


    @JsonProperty("user_id")
    private Integer peopleLikeId;

    @JsonProperty("updated")
    private LocalDateTime updated;

    @JsonProperty("post_id")
    private Integer likePost;
}
