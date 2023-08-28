package com.example.goodread.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "provinces")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Province {
    @Id
    String code;

    String name;

    String nameEn;

    String fullName;

    String fullNameEn;

    String codeName;

}
