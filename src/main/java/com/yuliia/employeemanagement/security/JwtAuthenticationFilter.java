package com.yuliia.employeemanagement.security;

import com.yuliia.employeemanagement.entity.Employee;
import com.yuliia.employeemanagement.service.EmployeeService;
import com.yuliia.employeemanagement.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final EmployeeService employeeService;

    public JwtAuthenticationFilter(JwtService jwtService, EmployeeService employeeService) {
        this.jwtService = jwtService;
        this.employeeService = employeeService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = authHeader.substring(7);

        String dni = jwtService.extractClaim(jwtToken, "sub");

        if (dni != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            Employee employee = employeeService.findEmployeeById(dni);

            if (employee != null && jwtService.isTokenValid(jwtToken, dni)) {

                String role = jwtService.extractClaim(jwtToken, "role");

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        employee, null, employee.getAuthorities() // Використовуємо роль з бази або токена
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
