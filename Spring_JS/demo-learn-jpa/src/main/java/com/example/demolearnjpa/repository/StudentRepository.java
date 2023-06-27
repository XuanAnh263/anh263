package com.example.demolearnjpa.repository;

import com.example.demolearnjpa.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository  extends JpaRepository<Student, Integer> {

    //use method query
    //Tim kiem theo ten va phan trang
    Page<Student> findByName(String name, Pageable pageable);

    Page<Student> findByNameContainingIgnoreCase(String name, Pageable pageable);

    List<Student> findByNameOrderByNameDesc(String name); //use keyword orderby (c1: nhanh nhat)
    List<Student> findByName(String name, Sort sort); //use param sort (c2: linh hoat, ap dung cho nhieu truong hop sap xep khac nhau)


    //use native query
    @Query(nativeQuery = true,
            value = "select * from student",
            countQuery = "select count(id) from student")
    Page<Student> getAllStudent(Pageable pageable);


    @Query(value = "SELECT * FROM student s WHERE LOWER(s.name) LIKE  CONCAT('%', LOWER(:keyword), '%')",
            countQuery = "select count(s.id) FROM student s WHERE LOWER(s.name) LIKE  CONCAT('%', LOWER(:keyword), '%') ",
            nativeQuery = true)
    Page<Student> findByNameContainingIgnoreCaseNative(@Param("keyword") String keyword , Pageable pageable);

    @Query(nativeQuery = true,
            value = "select * from student where name = ?1 order by name desc ")
    List<Student> findByNameSort(String name);




}
