package com.example.practive_jpa.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "supporters")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Supporter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Integer id;

    @Column(name = "Name", nullable = false)
    String name;

    @Column(name = "email",nullable = false, unique = true)
    String email;

    @Column(name = "phone", nullable = false)
    String phone;

    @Column(name = "avatar")
    String avatar;

//    @OneToMany(mappedBy = "supporter", cascade = CascadeType.ALL, orphanRemoval = true)
// chuwa can thiet mappby khi nao can su dung thi mappby
//    List<Course> courseList;
}
