package com.example.socialwave.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "full_name")
    String username;

    @Column(name = "date_of_birth")
    LocalDate dob;

    @Column(name = "gender")
    String gender;

    @Column(name = "avatar")
    String avatar;

    @Column(name = "phone")
    String phone;

    @Column(name = "address")
    String address;


    LocalDateTime deletedAt;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "user_role",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles = new HashSet<>();
}
