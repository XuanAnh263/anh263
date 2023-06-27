package com.example.practice_jpa2.model.response;

import com.example.practice_jpa2.entity.Supporter;
import com.example.practice_jpa2.entity.Topic;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseResponse {

    Integer id;
    String name;

    String description;

    String type;

    String thumbnail;


    Supporter supporter;


    Set<Topic> topics;
}
