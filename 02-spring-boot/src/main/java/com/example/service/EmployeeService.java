package com.example.service;

import com.example.persistence.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    List<Employee> findByFirstNameIgnoreCase(String firstName);

    void insert(Employee employee);
}
