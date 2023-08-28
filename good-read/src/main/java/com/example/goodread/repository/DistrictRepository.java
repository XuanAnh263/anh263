package com.example.goodread.repository;


import com.example.goodread.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District, String> {

    Optional<List<District>> findAllByProvinceCode(String provinceCode);
}