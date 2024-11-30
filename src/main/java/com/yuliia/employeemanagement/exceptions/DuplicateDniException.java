package com.yuliia.employeemanagement.exceptions;

public class DuplicateDniException extends AppException {

    public DuplicateDniException(String dni) {
        super("Employee with DNI " + dni + " already exists");
    }
}
