package com.example.controller;

import com.example.entity.Employee;
import com.example.model.Views;
import com.example.repositories.EmployeeRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private final EmployeeRepository employeeRepository;

    public EmployeesController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("")
    @JsonView(Views.EmployeeView.class)
    public Iterable<Employee> getEmployees() {
        return this.employeeRepository.findAll();
    }

}
