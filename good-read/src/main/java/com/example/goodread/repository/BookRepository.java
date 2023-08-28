package com.example.goodread.repository;


import com.example.goodread.entity.Book;
import com.example.goodread.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByCategories(Category category);

    Page<Book> findAllByTitleOrAuthorContainingIgnoreCase(String title, String author, Pageable pageable);

    Page<Book> findAllByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Book> findAllByAuthorContainingIgnoreCase(String author, Pageable pageable);

    @Query("select b from Book b where lower(b.title) like :title and lower(b.author) like :author")
    Page<Book> findAllByAuthorContainingIgnoreCase1(String title, String author, Pageable pageable);

}