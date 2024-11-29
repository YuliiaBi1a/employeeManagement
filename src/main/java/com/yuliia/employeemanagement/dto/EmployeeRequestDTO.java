package com.yuliia.employeemanagement.dto;

import java.util.List;

public class EmployeeRequestDTO {
    private String name;
    private String surname;
    private String dni;
    private String address;
    private String role;
    private List<Long> departmentIds;

    // getters
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDni() {
        return dni;
    }

    public String getRole() {
        return role;
    }

    public List<Long> getDepartmentIds() {
        return departmentIds;
    }

    public String getAddress() {
        return address;
    }

}
