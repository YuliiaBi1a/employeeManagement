# Employee Management System

## 📚 Table of Contents
1. [✏Project Overview](#project-overview)
2. [🛠Technologies Used](#technologies-used)
3. [📂Project Structure](#project-structure)
4. [🔑Key Features](#key-features)
5. [🔍API Endpoints](#api-endpoints)
6. [🔐Authentication](#authentication)
7. [✔Code Principles](#code-principles)
8. [📀Installation & Usage](#installation--usage)
9. [🧪Testing](#testing)
10. [📈Entity Relationship Diagram](#entity-relationship-diagram)
11. [🚀Planned Improvements](#planned-improvements)

---

## ✏Project Overview
The Employee Management System is a RESTful web application developed in Java using Spring Boot. It is designed for handling CRUD operations related to employees, departments, and roles, with role-based access control implemented via JWT authentication.

### 🎯 Objectives
- Implement RESTful APIs for CRUD operations on employees, departments, and roles.
- Ensure role-based access control (Administrators can modify data; Consultants have read-only access).
- Use JWT authentication, secured with a simple `DNI` check for user identification.

---

## 🛠Technologies Used
- **Programming Language**: Java (latest stable version)
- **Framework**: Spring Boot (embedded Tomcat server)
- **Database**: PHPMyAdmin
- **Security**: Spring Security and JWT for authentication
- **Testing**: JUnit 5, Mockito, MockMvc
- **Serialization/Deserialization**: Jackson
- **Build Tool**: Maven
- **Development Tools**: IntelliJ IDEA, Postman, Insomnia, XAMPP

---

## 📂Project Structure
``` 
        com.yuliia.employeemanagement
        │
        ├── controller
        │   ├── EmployeeController.java
        │   └── AuthController.java
        │
        ├── dto
        │   ├── EmployeeRequestDTO.java
        │   ├── AuthRequest.java
        │   └── AuthResponse.java
        │
        ├── entity
        │   ├── Employee.java
        │   ├── Department.java
        │   └── Role.java
        │
        ├── exceptions
        │   ├── GlobalExeptionHandler.java
        │   ├── AppExeption.java
        │   ├── InvalidDepartmentException.java
        │   ├── InvalidRoleException.java
        │   ├── NoDniFoundException.java
        │   ├── NoEmployeesFoundException.java
        │   └── DublicateDniExeption.java
        │ 
        ├── repository
        │   ├── EmployeeRepository.java
        │   ├── DepartmentRepository.java
        │   └── RoleRepository.java
        │
        ├── service
        │   ├── EmployeeService.java
        │   ├── DepartmentService.java
        │   ├── JwtService.java
        │   └── RoleService.java
        │
        ├── security
        │   └── SecurityConfig.java
        │   └── JwtAuthenticationFilter.java
        │
        ├── EmployeemanagementApplication.java
        └── resources
        ├── application.properties
        ├── static
        └── templates
```
---

## 🔑Key Features
- **Role-based Access Control**: Implemented with JWT, using `DNI` for authentication.
- **CRUD Operations**: Full support for employee, department, and role management.
- **JWT Token Generation**: Token is generated using `DNI` as the primary identifier.
- **Error Handling**: Custom exceptions for managing various error cases (e.g., employee not found, invalid role).

---

##  🔍API Endpoints
### Authentication
- **Login (Generate Token)**: `POST /api/auth`
    - **Request Body**:
      ```json
      {
        "dni": "12345"
      }
      ```
    - **Response**:
      ```json
      {
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
      }
      ```
### 🛠️Employee Endpoints Table

| **HTTP Method** | **Endpoint**             | **Description**                                 | **Authorization**        |
|------------------|--------------------------|-------------------------------------------------|--------------------------|
| `POST`          | `/api/employees`         | Create a new employee.                         | Administrator Only       |
| `GET`           | `/api/employees`         | Retrieve a list of all employees.              | Administrator/Consultant |
| `GET`           | `/api/employees/{dni}`   | Retrieve details of a specific employee by DNI.| Administrator/Consultant |
| `PUT`           | `/api/employees/{dni}`   | Update an existing employee by DNI.            | Administrator Only       |
| `DELETE`        | `/api/employees/{dni}`   | Delete an employee by DNI.                     | Administrator Only       |

---

## 🔐Authentication
### JWT-based Authentication
Authentication is handled through JWT tokens, generated by the `/api/auth` endpoint. This endpoint accepts a `DNI` as the only parameter and checks for the existence of an employee with the provided `DNI`.

### Login Endpoint
```java
@PostMapping()
public ResponseEntity<?> login(@RequestBody AuthRequest request) {
    Employee employee = employeeService.findEmployeeById(request.dni());
    if (employee == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Employee with DNI not found");
    }

    String token = jwtService.generateToken(request.dni(), employee.getRole().getName(), employee.getName());

    return ResponseEntity.ok(new AuthResponse(token));
}
```
#### Steps:

- The client sends a POST request with the DNI in the body.
- The controller checks the DNI against the employee database.
- If the DNI exists, a JWT token is generated and returned.
- The token must be included in the Authorization header for subsequent requests
```
Authorization: Bearer <your-jwt-token>
```
### Download CRUD Simulation for Insomnia

To test the CRUD operations in Insomnia, you can find the pre-configured JSON file in package utils:
src/main/java/com/yuliia/employeemanagement/utils/insomnia_crud_employee.json

### How to Use
1. Copy the file.
2. Open Insomnia.
3. Go to `Application` > `Preferences` > `Data` > `Import Data`.
4. Select `From File` and choose the copied `insomnia-crud-simulation.json` file.
5. Start testing the CRUD simulation.
   
---

## ✔Code Principles

### Clean Code
Emphasis on readability and maintainability.

### SOLID Principles 
- **Single Responsibility**: 
Each class has a single, well-defined purpose.
- **Open/Closed**:
The code is open for extension but closed for modification.
- **Liskov Substitution**:
Subtypes can be substituted for their base types without affecting behavior.
- **Interface Segregation**:
Interfaces are designed to have minimal and specific methods.
- **Dependency Inversion**
High-level modules depend on abstractions, not concrete classes.

### Error Handling
Custom exception classes to handle different types of errors gracefully.

## 📀Installation & Usage

### Prerequisites
- Java 17 or later
- Maven 3.x
- Git
- XAMPP
- PHPMyAdmin

### Steps to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/YuliiaBi1a/employeeManagement
    ```
2. Navigate to the project directory:
    ```
    cd employee-management
    ```
3. Build the project using Maven:
    ``` bash
   mvn clean install
    ```
4. Open XAMPP, run Apache (port 80, 443) and MySQL (port 3306) // o install XAMPP if you still haven`t it (https://www.apachefriends.org/)
5. Go to http://localhost/dashboard/ and choose PHPMyAdmin
6. Create an empty database from SQL window of PHPMyAdmin:
    ``` sql
    CREATE DATABASE employee_db;
    ```
7. Run the application:
    ``` bash
   mvn spring-boot:run
    ```
8. Access the application at http://localhost:8080
9. After the first launch, all the necessary tables and records in them will be created automatically.
10. To be able to test the application, the last step is to create an employee with the administrator role:
    ```sql
    INSERT INTO `employee` (`dni`, `address`, `name`, `surname`, `role_id`) VALUES ('ABC123460', 'New street', 'Admin', 'Admin', '1');
    INSERT INTO `employee_department` (`employee_dni`, `department_id`) VALUES ('ABC123460', '2');
    ```    
🛢 **I also leave here a sample database that you can import into PHPMyAdmin** - src/main/java/com/yuliia/employeemanagement/utils/employee_db.sql

---

## 🧪Testing
The project includes JUnit tests with MockMvc for testing REST endpoints. Unit tests validate CRUD operations and JWT-based authentication.

## 📈Entity Relationship Diagram
[ER Model](src/main/java/com/yuliia/employeemanagement/utils/mer-db-employee-manag.png)

---

## 🚀Planned Improvements

- **Enhanced User Authorization**: Create a separate `USER` table in the database to enable full user registration functionality on the frontend.
- **Use of Lombok**: Improve the readability of entity classes by using Lombok annotations.
- **Data Format Validation**: Validate the format of specific fields, such as DNI.
- **Add `EmployeeResponseDTO`**: Limit data visibility for non-admin users when retrieving employee details.
- **Advanced Search Functionality**: Allow users to search by additional attributes beyond just DNI.
- **Complete Functional Testing**: Finalize testing of all functional aspects of the application.
- **Frontend Development and Integration**: Build the frontend and connect it with the backend.
