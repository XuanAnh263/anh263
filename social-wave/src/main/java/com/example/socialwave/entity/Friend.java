package com.example.socialwave.entity;

import com.example.socialwave.statics.FriendStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Builder
@ToString
@Entity
@Table(name = "friends")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(targetEntity = User.class)
            @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(targetEntity = User.class)
            @JoinColumn(name = "friend_id")
    User friend;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    FriendStatus status;



    public Friend(User user, User friend, FriendStatus status) {
        this.user = user;
        this.friend = friend;
        this.status = status;
    }


}
