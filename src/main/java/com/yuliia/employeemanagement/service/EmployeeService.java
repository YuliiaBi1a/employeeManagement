package com.yuliia.employeemanagement.service;

import com.yuliia.employeemanagement.dto.EmployeeRequestDTO;
import com.yuliia.employeemanagement.entity.Department;
import com.yuliia.employeemanagement.entity.Employee;
import com.yuliia.employeemanagement.entity.Role;
import com.yuliia.employeemanagement.repository.DepartmentRepository;
import com.yuliia.employeemanagement.repository.EmployeeRepository;
import com.yuliia.employeemanagement.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           DepartmentRepository departmentRepository,
                           RoleRepository roleRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Employee createEmployee(EmployeeRequestDTO request) {

        if (employeeRepository.existsById(request.getDni())) {
            throw new RuntimeException("Employee with DNI " + request.getDni() + " already exists");
        }

        Role role = roleRepository.findByName(request.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        List<Department> departmentList = departmentRepository.findAllById(request.getDepartmentIds());
        if (departmentList.isEmpty()) {
            throw new RuntimeException("No valid departments found");
        }

        Set<Department> departments = new HashSet<>(departmentList);

        Employee employee = new Employee(
                request.getDni(),
                request.getName(),
                request.getSurname(),
                request.getAddress(),
                role,
                departments
        );

        return employeeRepository.save(employee);
    }
}
