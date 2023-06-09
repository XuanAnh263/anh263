package com.example.demo_zalo_part1.repository;



import com.example.demo_zalo_part1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
//    public  User findByEmail(String email);

}