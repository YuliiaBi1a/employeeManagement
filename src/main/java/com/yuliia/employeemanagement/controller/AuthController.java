package com.yuliia.employeemanagement.controller;

import com.yuliia.employeemanagement.dto.AuthRequest;
import com.yuliia.employeemanagement.dto.AuthResponse;
import com.yuliia.employeemanagement.entity.Employee;
import com.yuliia.employeemanagement.service.EmployeeService;
import com.yuliia.employeemanagement.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtService jwtService;
    private final EmployeeService employeeService;

    public AuthController(JwtService jwtService, EmployeeService employeeService) {
        this.jwtService = jwtService;
        this.employeeService = employeeService;
    }

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {

        Employee employee = employeeService.findEmployeeById(request.dni());
        if (employee == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Employee with DNI not found");
        }

        String token = jwtService.generateToken(request.dni(), employee.getRole().getName(), employee.getName());

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
