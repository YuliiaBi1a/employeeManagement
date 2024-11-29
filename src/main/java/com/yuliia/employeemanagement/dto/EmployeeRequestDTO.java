package com.yuliia.employeemanagement.dto;

import java.util.List;

public record EmployeeRequestDTO(
        String dni,
        String name,
        String surname,
        String address,
        String role,
        List<Long> departmentIds
) {}
