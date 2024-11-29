package com.yuliia.employeemanagement.controller;

import com.yuliia.employeemanagement.dto.EmployeeRequestDTO;
import com.yuliia.employeemanagement.entity.Employee;
import com.yuliia.employeemanagement.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/employees")
    public class EmployeeController {

        private final EmployeeService employeeService;

        public EmployeeController(EmployeeService employeeService) {
            this.employeeService = employeeService;
        }

        @PostMapping
        public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeRequestDTO request) {
            Employee newEmployee = employeeService.createEmployee(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
        }
    }

