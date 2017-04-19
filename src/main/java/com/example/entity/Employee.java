package com.example.entity;

import com.example.model.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.EmployeeView.class)
    private Long id;
    @JsonView(Views.EmployeeView.class)
    private String name;
    @JsonView(Views.ManagerView.class)
    private int salary;
    @JsonView(Views.EmployeeView.class)
    private Long managerId;


}
