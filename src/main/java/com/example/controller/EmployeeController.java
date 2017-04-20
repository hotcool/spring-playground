package com.example.controller;

import com.example.entity.Employee;
import com.example.model.Views;
import com.example.repositories.EmployeeRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/admin/employees")
    @JsonView(Views.ManagerView.class)
    public Iterable<Employee> getEmployees() {
        return this.employeeRepository.findAll();
    }


    @GetMapping("/me")
    public Employee getMe(@AuthenticationPrincipal Employee employee) {
        return employee;
    }
}
