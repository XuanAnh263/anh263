package com.example.socialwave.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpRequest {
//    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("password")
    private String password;
}
