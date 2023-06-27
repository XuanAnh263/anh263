package com.example.practice_jpa2.entity;

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
@Entity
@Table(name = "course")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "type", nullable = false)
    String type;

    @Column(name = "thumbnail")
    String thumbnail;

    @ManyToOne
    @JoinColumn(name = "supporter_id")
    Supporter supporter;

    @ManyToMany
    @JoinTable(name = "course_topic",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id"))
    Set<Topic> topics = new LinkedHashSet<>();
}
