package com.yuliia.employeemanagement.repository;

import com.yuliia.employeemanagement.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
