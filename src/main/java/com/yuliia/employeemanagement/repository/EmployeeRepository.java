package com.yuliia.employeemanagement.repository;

import com.yuliia.employeemanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
