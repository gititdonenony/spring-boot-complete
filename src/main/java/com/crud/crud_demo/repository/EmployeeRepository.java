package com.crud.crud_demo.repository;

import com.crud.crud_demo.dto.EmployeeResponse;
import com.crud.crud_demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
