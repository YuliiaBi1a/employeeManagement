package com.yuliia.employeemanagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuliia.employeemanagement.dto.EmployeeRequestDTO;
import com.yuliia.employeemanagement.entity.Employee;
import com.yuliia.employeemanagement.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateEmployee() throws Exception {
        EmployeeRequestDTO request = new EmployeeRequestDTO("12345", "John", "Doe", "123 Street", "ROLE_ADMINISTRATOR", List.of(1L));
        Employee employee = new Employee("12345", "John", "Doe", "123 Street", null, null);

        when(employeeService.createEmployee(request)).thenReturn(employee);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.dni").value("12345"))
                .andExpect(jsonPath("$.name").value("John"));

        verify(employeeService, times(1)).createEmployee(request);
    }

    @Test
    void testGetAllEmployees() throws Exception {
        List<Employee> employees = List.of(new Employee("12345", "John", "Doe", "123 Street", null, null));
        when(employeeService.findAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].dni").value("12345"));

        verify(employeeService, times(1)).findAllEmployees();
    }

    @Test
    void testGetEmployeeById() throws Exception {
        Employee employee = new Employee("12345", "John", "Doe", "123 Street", null, null);
        when(employeeService.findEmployeeById("12345")).thenReturn(employee);

        mockMvc.perform(get("/api/employees/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dni").value("12345"))
                .andExpect(jsonPath("$.name").value("John"));

        verify(employeeService, times(1)).findEmployeeById("12345");
    }

    @Test
    void testUpdateEmployee() throws Exception {
        EmployeeRequestDTO request = new EmployeeRequestDTO("12345", "John", "Smith", "456 Street", "ROLE_CONSULTANT", List.of(1L));
        Employee updatedEmployee = new Employee("12345", "John", "Smith", "456 Street", null, null);

        when(employeeService.updateEmployee("12345", request)).thenReturn(updatedEmployee);

        mockMvc.perform(put("/api/employees/12345")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dni").value("12345"))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.surname").value("Smith"));

        verify(employeeService, times(1)).updateEmployee("12345", request);
    }

    @Test
    void testDeleteEmployee() throws Exception {
        doNothing().when(employeeService).deleteEmployeeById("12345");

        mockMvc.perform(delete("/api/employees/12345"))
                .andExpect(status().isNoContent());

        verify(employeeService, times(1)).deleteEmployeeById("12345");
    }
}
