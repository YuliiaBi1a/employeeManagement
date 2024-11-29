package com.yuliia.employeemanagement.entity;

import jakarta.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // constructors
    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // setter
    public void setName(String name) {
        this.name = name;
    }
}