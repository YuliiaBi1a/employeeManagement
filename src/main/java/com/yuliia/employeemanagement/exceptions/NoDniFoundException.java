package com.yuliia.employeemanagement.exceptions;

public class NoDniFoundException extends AppException {

    public NoDniFoundException(String dni) {
        super("Employee with DNI " + dni + " not found");
    }
}
