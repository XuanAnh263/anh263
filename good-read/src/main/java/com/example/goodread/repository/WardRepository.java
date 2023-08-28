package com.example.goodread.repository;


import com.example.goodread.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WardRepository extends JpaRepository<Ward, String> {

    Optional<List<Ward>> findAllByDistrictCode(String districtCode);

    Ward findByCode(String wardCode);
}