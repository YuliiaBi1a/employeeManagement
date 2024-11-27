package com.yuliia.employeemanagement.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "departments")
    private Set<Employee> employees = new HashSet<>();

    //constructors
    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }
    //getters
    public String getName() {
        return name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    //setters

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
