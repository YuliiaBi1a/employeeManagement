package com.yuliia.employeemanagement.exceptions;

public class NoEmployeesFoundException extends AppException {

    public NoEmployeesFoundException() {

        super("No employees found");
    }
}
