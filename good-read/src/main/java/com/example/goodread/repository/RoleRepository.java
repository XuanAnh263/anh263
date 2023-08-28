package com.example.goodread.repository;

import com.example.goodread.entity.Role;
import com.example.goodread.statics.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(Roles name);

}
