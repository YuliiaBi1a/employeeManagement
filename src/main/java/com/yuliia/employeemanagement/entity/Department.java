package com.yuliia.employeemanagement.entity;

import jakarta.persistence.*;

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
    //setters

    public void setName(String name) {
        this.name = name;
    }
}
