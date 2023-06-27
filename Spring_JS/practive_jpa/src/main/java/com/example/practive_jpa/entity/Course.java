package com.example.practive_jpa.entity;

import com.example.practive_jpa.statics.Type;
import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "courses")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Type type;

    @Column(name = "thumbnail")
    String thumbnail;

    @ManyToOne
    @JoinColumn(name = "supporter_id")
    Supporter supporter;

    @ManyToMany
    @JoinTable(name = "course_topic",
    joinColumns = @JoinColumn(name = "course_id"),
    inverseJoinColumns = @JoinColumn(name = "topic_id"))
    List<Topic> topics;
}
