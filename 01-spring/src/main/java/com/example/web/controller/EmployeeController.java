package com.example.web.controller;

import com.example.persistence.entity.Employee;
import com.example.service.EmployeeService;
import com.example.web.form.EmployeeForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "employee/index";
    }

    @GetMapping("/findByFirstName")
    public String findByFirstName(@RequestParam(value = "firstName", defaultValue = "") String firstName, Model model) {
        List<Employee> employees = employeeService.findByFirstNameContainingIgnoreCase(firstName);
        model.addAttribute("employees", employees);
        return "employee/index";
    }

    @GetMapping("/insertMain")
    public String insertMain(Model model) {
        model.addAttribute("employeeForm", new EmployeeForm(null, null, null));
        return "employee/insertMain";
    }

    @PostMapping("/insertComplete")
    public String insertComplete(@Validated EmployeeForm employeeForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // log EmployeeForm
            System.out.println(employeeForm);
            return "employee/insertMain";
        }
        Employee employee = employeeForm.convertToEntity();
        employeeService.insert(employee);
        return "redirect:index";
    }

}
