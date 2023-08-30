package com.example.socialwave.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserRequest {

    @ApiModelProperty(
            example="thaimeo",
            notes="Username cannot be empty",
            required=true
    )
    @JsonProperty("phone_number")
    private String phoneNumber;

    @ApiModelProperty(
            example="123456",
            notes="password cannot be empty",
            required=true
    )
    @JsonProperty("password")
    private String password;

    @ApiModelProperty(
            example="Da",
            notes="First name cannot be empty",
            required=true
    )
    @JsonProperty("first_name")
    private String  firstName;


    @ApiModelProperty(
            example="Th",
            notes="Last name cannot be empty",
            required=true
    )
    @JsonProperty("last_name")
    private String lastName;

    @ApiModelProperty(
            example="male",
            notes="Gender cannot be empty , 1-Male , 2-Female",
            required=true
    )
    @JsonProperty("gender")
    private String gender;



    @ApiModelProperty(
            example="1999-06-02T21:33:45.249967",
            notes="Birth Date  cannot be empty",
            required=true
    )
    @JsonProperty("dob")
    private LocalDate dob;


    @ApiModelProperty(
            example="1999-06-02T21:33:45.249967",
            notes="Joined Date  cannot be empty",
            required=true
    )
    @JsonProperty("joined_date")
    private LocalDateTime joinedDate;

    @ApiModelProperty(
            example="enable",

            required=true
    )
    @JsonProperty("status")
    private String status;

    @ApiModelProperty(
            example="user",
            notes=" 1-ADMIN , 2-STAFF",
            required=true
    )
    @JsonProperty("authority")
    private String authority;

//
//    @ApiModelProperty(
//            example="user",
//            notes=" 1",
//            required=true
//    )
//    @JsonProperty("user_id")
//    private User user;




    @ApiModelProperty(
            example="user",
            notes=" link",
            required=true
    )
    @JsonProperty("link_avatar")
    private String linkAvatar;

}
