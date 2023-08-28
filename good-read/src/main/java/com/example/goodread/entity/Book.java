package com.example.goodread.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book extends BaseEntity {
    String image;

    String title;

    @ManyToMany
    @JoinTable(name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    Set<Category> categories;

    String author;

    @Column(columnDefinition = "TEXT")
    String description;

    @Column(nullable = false)
    double rating;

    LocalDate published;

    String buyBook;

    @Column(nullable = false)
    Integer pages;
}
