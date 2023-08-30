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
public class CreateFriendRequest {


    @JsonProperty("user_a")
    private Integer userA;

    @JsonProperty("user_b")
    private Integer userB;


    @JsonProperty("created")
    private LocalDateTime created;

    @JsonProperty("state")
    private String state;
}
