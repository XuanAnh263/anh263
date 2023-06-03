package com.example.demolearnjpa;

import com.example.demolearnjpa.entity.Employee;
import com.example.demolearnjpa.entity.User;
import com.example.demolearnjpa.repository.EmployeeRepository;
import com.example.demolearnjpa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;



@SpringBootTest
class DemoLearnJpaApplicationTests {



    private UserRepository userRepository;


    @Autowired
    private EmployeeRepository employeeRepository;

//    @Test
//    void save_user() {
//        User user = new User(null, "Xuaan anh", "anh@gmail.com", LocalDate.now().minusYears(26));
//        userRepository.save(user);
//    }
//
//    @Test
//    void save_user_list() {
//        List<User> userList = List.of(
//                new User(null, " anh", "anh123@gmail.com", LocalDate.now().minusYears(26)),
//                new User(null, "Hoang", "hoang55@gmail.com", LocalDate.now().minusYears(30)),
//                new User(null, "bang", "bang123@gmail.com", LocalDate.now().minusYears(15))
//        );
//        userRepository.saveAll(userList);
//    }

    @Test
    void saveEmployeeList() {
        List<Employee> employees = List.of(
                new Employee(null, " anh", "phong ban A", 500000L,LocalDate.now()),
                new Employee(null, "Hoang", "phong ban B",450000L, LocalDate.now()),
                new Employee(null, "bang", "phong ban C",430000L, LocalDate.now()),
                new Employee(null, "son", "phong ban D",650000L, LocalDate.now())
        );
        employeeRepository.saveAll(employees);
    }



}
