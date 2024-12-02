package com.yuliia.employeemanagement.service;

import com.yuliia.employeemanagement.entity.Role;
import com.yuliia.employeemanagement.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class RolService {
    private final RoleRepository roleRepository;

    public RolService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void initRoles() {
        if (roleRepository.count() == 0) {
            // Збереження ролей із префіксом ROLE_
            roleRepository.save(new Role("ROLE_ADMINISTRATOR"));
            roleRepository.save(new Role("ROLE_CONSULTANT"));
        }
    }
}
