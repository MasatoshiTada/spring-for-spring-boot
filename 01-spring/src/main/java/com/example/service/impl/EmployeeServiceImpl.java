package com.example.service.impl;

import com.example.persistence.entity.Employee;
import com.example.persistence.repository.EmployeeRepository;
import com.example.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<Employee> findByFirstNameIgnoreCase(String firstName) {
        return employeeRepository.findByFirstNameContaining(firstName);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insert(Employee employee) {
        employeeRepository.save(employee);
    }
}
