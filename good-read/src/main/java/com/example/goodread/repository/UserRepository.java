package com.example.goodread.repository;


import com.example.goodread.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndActivated(String email, Boolean activated);


}