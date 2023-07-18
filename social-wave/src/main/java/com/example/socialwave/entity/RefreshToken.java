package com.example.socialwave.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefreshToken extends BaseEntity {

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class)
    User user;

    @Column(name = "refresh_token")
    String refreshToken;

//    @Type(type= "org.hibernate.type.NumericBooleanType")
    @Column(columnDefinition = "boolean default false")
    boolean invalidated;

}
