package com.example.be.service;

import com.example.be.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface IEmployeeService {
    Page<Employee> findAll(String fullname, String phone, Pageable pageable);
    void deleteEmployee(int id);
    Employee findById(int id);
    void createNewEmployee(Employee e);
    void updateEmployee(Employee e);
}
