package com.yuliia.employeemanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
//POST
    @ExceptionHandler(DuplicateDniException.class)
    public ResponseEntity<String> handleDuplicateDni(DuplicateDniException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<String> handleRoleNotFound(InvalidRoleException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDepartmentException.class)
    public ResponseEntity<String> handleInvalidDepartment(InvalidDepartmentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    //GET
    @ExceptionHandler(NoEmployeesFoundException.class)
    public ResponseEntity<String> handleNotFoundEmployee(NoEmployeesFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    @ExceptionHandler(NoDniFoundException.class)
    public ResponseEntity<String> handleNotFoundDni(NoDniFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
}

