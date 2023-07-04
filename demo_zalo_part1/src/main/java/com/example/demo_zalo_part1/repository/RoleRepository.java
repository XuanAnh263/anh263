package com.example.demo_zalo_part1.repository;

import com.example.demo_zalo_part1.entity.Role;

import com.example.demo_zalo_part1.statics.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(Roles name);

}
