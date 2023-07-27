package com.example.socialwave.entity;


import com.example.socialwave.statics.ConversationStatus;
import com.example.socialwave.statics.ConversationType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@ToString
@Entity
@Table(name = "Conversations")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "avatar")
    String avatar;

    @Column(name = "description")
    String description;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    ConversationType type;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    ConversationStatus status;

    @CreatedDate
    LocalDateTime creatDateTime;

    @LastModifiedDate
    LocalDateTime lastModifiedDateTime;

    Long ownerId;

    @ManyToMany
    @JoinTable(
            name = "participant",
            joinColumns = @JoinColumn(name = "conversation_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
     Set<User> users = new HashSet<>();

    public Conversation(Long id) {
        this.id =  id;
    }

    public Conversation(Long id,String name, String avatar, String description, Long ownerId) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.description = description;
        this.ownerId = ownerId;
    }

    public Conversation(Long id, String name, String avatar, String description, ConversationType type, ConversationStatus status, LocalDateTime creatDateTime, LocalDateTime lastModifiedDateTime, Long ownerId) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.description = description;
        this.type = type;
        this.status = status;
        this.creatDateTime = creatDateTime;
        this.lastModifiedDateTime = lastModifiedDateTime;
        this.ownerId = ownerId;
    }

    public void addUser(User user) {
        this.users.add(user);
        user.getConversations().add(this);
    }

    public void removeUser(User user) {
        this.users.remove(user);
        user.getConversations().remove(this);
    }


}
