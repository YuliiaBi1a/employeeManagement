package com.yuliia.employeemanagement.service;

import com.yuliia.employeemanagement.dto.EmployeeRequestDTO;
import com.yuliia.employeemanagement.entity.Department;
import com.yuliia.employeemanagement.entity.Employee;
import com.yuliia.employeemanagement.entity.Role;
import com.yuliia.employeemanagement.exceptions.*;
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

        if (employeeRepository.existsById(request.dni())) {
            throw new DuplicateDniException(request.dni());
        }

        Role role = roleRepository.findByName(request.role()).orElse(null);
        if (role == null) {
            throw new InvalidRoleException(request.role());
        }

        List<Department> departmentList = departmentRepository.findAllById(request.departmentIds());
        if (departmentList.isEmpty()) {
            throw new InvalidDepartmentException(request.departmentIds());
        }

        Set<Department> departments = new HashSet<>(departmentList);

        Employee employee = new Employee(
                request.dni(),
                request.name(),
                request.surname(),
                request.address(),
                role,
                departments
        );

        return employeeRepository.save(employee);
    }

    @Transactional(readOnly = true)
    public List<Employee> findAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            throw new NoEmployeesFoundException();
        }
        return employees;
    }

    @Transactional(readOnly = true)
    public Employee findEmployeeById(String dni) {
        Optional<Employee> employeeOptional = employeeRepository.findById(dni);
        if (employeeOptional.isPresent()) {
            return employeeOptional.get();
        } else {
            throw new NoDniFoundException(dni);
        }
    }

    @Transactional
    public Employee updateEmployee(String dni, EmployeeRequestDTO request) {

        Optional<Employee> optionalEmployee = employeeRepository.findById(dni);
        if (optionalEmployee.isEmpty()) {
            throw new NoDniFoundException(dni);
        }
        Employee existingEmployee = optionalEmployee.get();

        existingEmployee.setName(request.name());
        existingEmployee.setSurname(request.surname());
        existingEmployee.setAddress(request.address());

        Optional<Role> optionalRole = roleRepository.findByName(request.role());
        if (optionalRole.isEmpty()) {
            throw new InvalidRoleException(request.role());
        }
        existingEmployee.setRole(optionalRole.get());

        List<Department> departmentList = departmentRepository.findAllById(request.departmentIds());
        if (departmentList.isEmpty()) {
            throw new InvalidDepartmentException(request.departmentIds());
        }
        existingEmployee.setDepartments(new HashSet<>(departmentList));

        return employeeRepository.save(existingEmployee);
    }


    @Transactional
    public void deleteEmployeeById(String dni) {
        Optional<Employee> employeeOptional = employeeRepository.findById(dni);
        if (employeeOptional.isEmpty()) {
            throw new NoDniFoundException(dni);
        }
        employeeRepository.deleteById(dni);
    }
}
