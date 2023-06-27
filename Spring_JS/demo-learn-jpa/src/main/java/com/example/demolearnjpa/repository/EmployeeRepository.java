package com.example.demolearnjpa.repository;

import com.example.demolearnjpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //jpa
    List<Employee> findByDepartment(String department);
    List<Employee> findBySalaryGreaterThan(Long salary);
    List<Employee> findByName(String name);
    List<Employee> findByNameContainingIgnoreCase(String key);
    List<Employee> findByNameStartingWith(String prefix);
    List<Employee> findBySalaryBetween(Long start, Long end);
    long countByDepartment(String department);
    List<Employee> findByDepartmentAndNameContaining(String department, String name);
    List<Employee> findByNameOrDepartment(String name, String department);
    List<Employee> findByJoiningDateAfter(LocalDate joiningDate);

    //native query
    @Query(value = "SELECT * FROM employee WHERE department = :department", nativeQuery = true)
    List<Employee> findByDepartmentNative(@Param("department") String department);

    @Query(value = "SELECT * FROM employee WHERE salary > :salary", nativeQuery = true)
    List<Employee> findBySalaryGreaterThanNative(@Param("salary") Long salary);

    @Query(value = "SELECT * FROM employee WHERE name = :name", nativeQuery = true)
    List<Employee> findByNameNative(@Param("name") String name);

    @Query(value = "SELECT * FROM employee WHERE LOWER(name) LIKE  CONCAT('%', LOWER(:keyword), '%')", nativeQuery = true)
    List<Employee> findByNameContainingIgnoreCaseNative(@Param("keyword") String keyword);

    @Query(value = "SELECT * FROM employee WHERE name LIKE CONCAT(:prefix, '%')", nativeQuery = true)
    List<Employee> findByNameStartingWithNative(@Param("prefix") String prefix);

    @Query(value = "SELECT * FROM employee WHERE salary BETWEEN :minSalary AND :maxSalary", nativeQuery = true)
    List<Employee> findBySalaryBetweenNative(@Param("minSalary") Long minSalary, @Param("maxSalary") Long maxSalary);

    @Query(value = "SELECT COUNT(*) FROM employee WHERE department = :department", nativeQuery = true)
    int countByDepartmentNative(@Param("department") String department);

    @Query(value = "SELECT * FROM  employee WHERE LOWER(name) LIKE CONCAT('%', LOWER(:keyword), '%') and  department = :department", nativeQuery = true)
    List<Employee> findByNameContainingIgnoreCaseAndDepartmentNative(@Param("keyword") String keyword, @Param("department") String department);

    @Query(value = "SELECT  * FROM employee WHERE  name = :name OR  department =:department", nativeQuery = true)
    List<Employee> findByNameOrDepartmentNative(@Param("name") String name, @Param("department") String department);

    @Query(value = "SELECT * FROM employee WHERE joiningDate > :date", nativeQuery = true)
    List<Employee> findByJoiningDateNative(@Param("date") LocalDate date);







}
