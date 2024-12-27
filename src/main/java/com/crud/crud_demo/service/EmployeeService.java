package com.crud.crud_demo.service;

import com.crud.crud_demo.dto.EmployeeRequest;
import com.crud.crud_demo.dto.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    public EmployeeResponse createEmployee(EmployeeRequest employeeRequest);

    public EmployeeResponse getEmployeeById(Long id);

    public List<EmployeeResponse> getAllEmployees();

    public EmployeeResponse updateEmployee(Long id, EmployeeRequest employeeRequest);

    public void deleteEmployee(Long id);
}
