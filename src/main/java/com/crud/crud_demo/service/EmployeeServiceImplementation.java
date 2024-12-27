package com.crud.crud_demo.service;

import com.crud.crud_demo.dto.EmployeeRequest;
import com.crud.crud_demo.dto.EmployeeResponse;
import com.crud.crud_demo.entity.Employee;
import com.crud.crud_demo.exception.EmployeeNotFoundException;
import com.crud.crud_demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImplementation implements EmployeeService{

    private EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeServiceImplementation(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setEmail(employeeRequest.getEmail());
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeResponse savedEmployeeResponse = new EmployeeResponse();
        savedEmployeeResponse.setId(savedEmployee.getId());
        savedEmployeeResponse.setFirstName(savedEmployee.getFirstName());
        savedEmployeeResponse.setLastName(savedEmployee.getLastName());
        savedEmployeeResponse.setEmail(savedEmployee.getEmail());
        return savedEmployeeResponse;
    }

    @Override
    public EmployeeResponse getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));

        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(employee.getId());
        employeeResponse.setFirstName(employee.getFirstName());
        employeeResponse.setLastName(employee.getLastName());
        employeeResponse.setEmail(employee.getEmail());
        return employeeResponse;
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        // Retrieve all employees from the repository
        List<Employee> employees = employeeRepository.findAll();

        // Map Employee entities to EmployeeResponse DTOs
        return employees.stream()
                .map(this::convertToEmployeeResponse)
                .collect(Collectors.toList());
    }
    // Helper method to convert Employee to EmployeeResponse
    private EmployeeResponse convertToEmployeeResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        response.setEmail(employee.getEmail());
        return response;
    }


    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest) {
        try {
            // Retrieve the employee by ID, or throw a custom exception if not found
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));

            // Update the employee details
            employee.setFirstName(employeeRequest.getFirstName());
            employee.setLastName(employeeRequest.getLastName());
            employee.setEmail(employeeRequest.getEmail());

            // Save the updated employee
            Employee updatedEmployee = employeeRepository.save(employee);

            // Convert the updated employee to EmployeeResponse
            return convertToEmployeeResponse(updatedEmployee);

        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while updating the employee", ex);
        }
    }


    @Override
    public void deleteEmployee(Long id) {
        try {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + id));


            employeeRepository.delete(employee);
        } catch (Exception ex) {
            throw new RuntimeException("An unexpected error occurred while deleting the employee", ex);
        }
    }


}
