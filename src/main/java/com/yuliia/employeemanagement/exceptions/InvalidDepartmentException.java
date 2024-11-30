package com.yuliia.employeemanagement.exceptions;

import java.util.List;
import java.util.stream.Collectors;

public class InvalidDepartmentException extends AppException {
    public InvalidDepartmentException(List<Long> departmentIds) {
        super("No valid departments found for the given IDs: "
                + departmentIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")));
    }
}
