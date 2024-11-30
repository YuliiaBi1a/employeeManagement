package com.yuliia.employeemanagement.controller;

import com.yuliia.employeemanagement.dto.EmployeeRequestDTO;
import com.yuliia.employeemanagement.entity.Employee;
import com.yuliia.employeemanagement.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeRequestDTO request) {
        Employee newEmployee = employeeService.createEmployee(request);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        List<Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable String id) {
        Employee employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/{dni}")
    public ResponseEntity<?> updateEmployee(@PathVariable String dni, @RequestBody EmployeeRequestDTO request) {
        Employee updatedEmployee = employeeService.updateEmployee(dni, request);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable String id) {
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>("Employee with ID " + id + " was successfully deleted", HttpStatus.OK);
    }
}

