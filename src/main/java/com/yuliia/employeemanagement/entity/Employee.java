package com.yuliia.employeemanagement.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @Column(nullable = false, unique = true)  //DNI = PK
    private String dni;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    private String address;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "employee_department",
            joinColumns = @JoinColumn(name = "employee_dni"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    private Set<Department> departments = new HashSet<>();

    // Constructors
    public Employee() {}

    public Employee(String dni, String name, String surname, String address, Role role, Set<Department> departments) {
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.role = role;
        this.departments = departments;
    }

    // Getters
    public String getDni() {
        return dni;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getAddress() {
        return address;
    }
    public Role getRole() {
        return role;
    }
    public Set<Department> getDepartments() {
        return departments;
    }

    //Setters
    public void setDni(String dni) {
        this.dni = dni;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    //methods
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role != null && role.getName() != null) {
            return Collections.singletonList(new SimpleGrantedAuthority(role.getName()));
        }
        return Collections.emptyList();
    }
}