package com.example.web.form;

import com.example.persistence.entity.Employee;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class EmployeeForm {

    @NotBlank
    @Length(min = 1, max = 40)
    private final String firstName;

    @NotBlank
    @Length(min = 1, max = 40)
    private final String lastName;

    @NotNull
    private final LocalDate joinedDate;

    public EmployeeForm(String firstName, String lastName, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate joinedDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.joinedDate = joinedDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public Employee convertToEntity() {
        Employee employee = new Employee();
        employee.firstName = firstName;
        employee.lastName = lastName;
        employee.joinedDate = joinedDate;
        return employee;
    }

    @Override
    public String toString() {
        return "EmployeeForm{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", joinedDate=" + joinedDate +
                '}';
    }
}
