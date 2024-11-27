package com.yuliia.employeemanagement.service;

import com.yuliia.employeemanagement.entity.Department;
import com.yuliia.employeemanagement.repository.DepartmentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;


@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @PostConstruct
    public void initDepartments() {

        if (departmentRepository.count() == 0) {

            departmentRepository.save(new Department("Contabilidad"));
            departmentRepository.save(new Department("Administración"));
            departmentRepository.save(new Department("Desarrollo"));
            departmentRepository.save(new Department("Dirección"));
        }
    }
}