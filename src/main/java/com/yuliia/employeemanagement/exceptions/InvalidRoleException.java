package com.yuliia.employeemanagement.exceptions;

public class InvalidRoleException extends AppException {
    public InvalidRoleException(String roleName) {
        super("Role " + roleName + " does not exist");
    }
}
