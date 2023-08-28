package com.example.goodread.repository;


import com.example.goodread.entity.Book;
import com.example.goodread.entity.Review;
import com.example.goodread.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewBookRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByUserAndBook(User user, Book book);


}