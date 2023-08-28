package com.example.goodread.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookSearchResponse {

    Long id;

    String image;

    String title;

    String categories;

    String author;

    String description;

    double rating;

    String ratingDetail;

    Integer pages;

    LocalDate published;

    String buyBook;

    String readingStatus;

    Integer countOfRatings;

    Integer countOfReview;

    String content;
}
