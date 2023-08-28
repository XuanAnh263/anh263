package com.example.goodread.repository;


import com.example.goodread.entity.Book;
import com.example.goodread.entity.ReadingBook;
import com.example.goodread.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReadingBookRepository extends JpaRepository<ReadingBook, Long> {

//    Page<ReadingBook> findAllByUser(User user, Pageable pageable);


    List<ReadingBook> findAllByUser(User user);

    Optional<ReadingBook> findAllByBook(Book book);

    Optional<ReadingBook> findByUserAndBook(User user, Book book);
}