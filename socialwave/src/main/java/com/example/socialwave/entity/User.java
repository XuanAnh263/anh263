package com.example.socialwave.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    Boolean activited;

    LocalDateTime deletedAt;

    public User(String email, String password, String username, Long id, Boolean activited) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.id = id;
        this.activited = activited;
    }

    @ManyToMany(mappedBy = "users")
    private Set<Conversation> conversations = new HashSet<>();



    // Constructors, getters và setters

    // Phương thức để thêm cuộc hội thoại cho người dùng
    public void addConversation(Conversation conversation) {
        this.conversations.add(conversation);
        conversation.getUsers().add(this);
    }

    // Phương thức để xoá cuộc hội thoại khỏi người dùng
    public void removeConversation(Conversation conversation) {
        this.conversations.remove(conversation);
        conversation.getUsers().remove(this);
    }

}
