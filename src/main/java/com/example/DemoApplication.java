package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
/*
    @Bean
    @Profile("default")
    public CommandLineRunner seedData(EmployeeRepository employeeRepository) {
        return (args) -> {
            employeeRepository.deleteAll();
            Employee employee = new Employee();
            employee.setName("Employee");
            employee.setSalary(24);
            employeeRepository.save(employee);
        };

    }
    */
}
