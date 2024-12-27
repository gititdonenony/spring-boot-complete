package com.crud.crud_demo.controller;

import com.crud.crud_demo.dto.EmployeeRequest;
import com.crud.crud_demo.dto.EmployeeResponse;
import com.crud.crud_demo.exception.EmployeeNotFoundException;
import com.crud.crud_demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse = employeeService.createEmployee(employeeRequest);

        // Return response with HTTP status CREATED (201)
        return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        try {
            EmployeeResponse employeeResponse = employeeService.getEmployeeById(id);
            return new ResponseEntity<>(employeeResponse, HttpStatus.OK); // Return HTTP 200 OK
        } catch (EmployeeNotFoundException ex) {
            throw ex;
        }
    }

    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest) {
        try {
            EmployeeResponse employeeResponse = employeeService.updateEmployee(id, employeeRequest);
            return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
        } catch (EmployeeNotFoundException ex) {
            throw ex;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EmployeeNotFoundException ex) {
            throw ex;
        }
    }
}

