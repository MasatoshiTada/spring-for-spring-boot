package com.example.persistence.repository;

import com.example.persistence.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByFirstNameContainingIgnoreCase(String firstName);
}
