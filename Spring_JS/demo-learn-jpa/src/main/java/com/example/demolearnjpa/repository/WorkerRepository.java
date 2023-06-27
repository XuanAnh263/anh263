package com.example.demolearnjpa.repository;

import com.example.demolearnjpa.dto.WorkerDto;
import com.example.demolearnjpa.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {

    //C2: JPQL
    @Query("""
        select new com.example.demolearnjpa.dto.WorkerDto(w.id, w.name, w.email)
        from Worker  w
        where w.email =?1
        """)
    WorkerDto getWorkerDtoByEmail(String email);

    @Query(nativeQuery = true, name = "getWorkerDtoUseNativeQuery")
    WorkerDto getWorkerDtoUseNativeQ(String email);

}
