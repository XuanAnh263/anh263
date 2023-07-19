package com.example.socialwave.repository;

import com.example.socialwave.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
    Optional<User> findById(Long id );

    User save(User user);
    User update(User user);
    void delete(User user);


}