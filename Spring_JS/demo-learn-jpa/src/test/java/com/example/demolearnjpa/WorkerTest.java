package com.example.demolearnjpa;

import com.example.demolearnjpa.dto.WorkerDto;
import com.example.demolearnjpa.entity.Worker;
import com.example.demolearnjpa.mapper.WorkerMapper;
import com.example.demolearnjpa.repository.WorkerRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;



@SpringBootTest
public class WorkerTest {
    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private Faker faker;

    @Test
    void saveWorker() {
        for (int i = 0; i < 10; i++) {
            Worker worker = Worker.builder()
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .password("38624" + i)
                    .build();
            workerRepository.save(worker);
        }
    }

    //c1: mapper
    @Test
    void queryDtoMapper() {
        List<Worker> workers = workerRepository.findAll();
        List<WorkerDto> workerDtoList = workers.stream()
                .map(WorkerMapper::toWorkerDto).toList();

        workerDtoList.forEach(System.out::println);
    }

    @Test
    void queryDtoMapper3rd() {
        ModelMapper mapper = new ModelMapper();

        List<Worker> workers = workerRepository.findAll();
        List<WorkerDto> workerDtoList = workers.stream()
                .map(worker -> mapper.map(worker, WorkerDto.class))
                .toList();

        workerDtoList.forEach(System.out::println);
    }

    //c1: JPQL
    @Test
    void queryDtoJPQL() {
        WorkerDto workerDto = workerRepository.getWorkerDtoByEmail("demetrius.mann@yahoo.com");
        System.out.println(workerDto);
    }

    //C3: native query
    @Test
    void queryDtoNavQuery() {
        WorkerDto workerDto = workerRepository.getWorkerDtoByEmail("titus.beatty@yahoo.com");
        System.out.println(workerDto);
    }
}
