package com.example.goodread.repository;


import com.example.goodread.entity.Book;
import com.example.goodread.entity.BookOfChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookOfChallengeRepository extends JpaRepository<BookOfChallenge, Long> {


    Optional<BookOfChallenge> findAllByBook(Book book);
}