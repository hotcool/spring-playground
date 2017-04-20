package com.example.entity;

import com.example.model.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
public class Employee implements UserDetails{

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

    @Column(unique=true)
    @JsonView(Views.EmployeeView.class)
    private String username;

    @JsonIgnore
    private String password;

    @JsonView(Views.EmployeeView.class)
    private String role;

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.role));
    }
}
