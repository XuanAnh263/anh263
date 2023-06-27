package com.example.practice_jpa2.model.request;

import com.example.practice_jpa2.entity.Supporter;
import com.example.practice_jpa2.entity.Topic;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpsertCourseRequest {
    Integer id;

    @Size(max = 100, message = "Name cannot over 100 characters")
    @NotBlank(message = "Name cannot be blank!")
    String name;

    @Min(value = 50, message = "Description cannot be less than 50")
    @NotBlank(message = "Description cannot be blank!")
    String description;

    @NotBlank(message = "Type cannot be blank!")
    String type;


    String thumbnail;

    @NotNull(message = "Please enter your Supporter ID")
    Integer supporterId;

    @NotNull(message = "Please enter the list")
    @Size(min = 1, message = "Topic must have least 1")
    List<Integer> topicIds;

}
