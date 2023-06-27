package com.example.demolearnjpa.repository;

import com.example.demolearnjpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    //method query
    Optional<User> findByEmail(String email);
    @Query("select u from User u where u.email=?1")
    Optional<User> findByEmailUsingJpql(String email);

//    List<User> findByName(String name);
//    List<User> findByNameContaining(String key);
//    List<User> findByNameContainingIgnoreCase(String key);
//    List<User> findByNameStartingWith(String prefix);
//    List<User> findByNameAndEmail(String name, String email);
//    List<User> findByNameOrEmail(String name, String email);
//    long countByName(String name);
//    boolean existsByEmail(String email);
//    Optional<User> findTop2ByDobAfter(LocalDate dob);
}
