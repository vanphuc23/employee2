package com.example.be.service;

import com.example.be.entity.Employee;
import com.example.be.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService implements IEmployeeService{
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Page<Employee> findAll(String fullname, String phone, Pageable pageable) {
        return employeeRepository.findAll(fullname,phone,pageable);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteEmployeeById(id);
    }

    @Override
    public Employee findById(int id) {
        return employeeRepository.findEmployeeById(id);
    }

    @Override
    public void createNewEmployee(Employee e) {
        employeeRepository.createNewEmployee(e.getEmployeeIden(), e.getImage(), e.getBirthday(), e.isGender(), e.getFullname(), e.getIdCard(), e.getEmail(), e.getPhone(), e.getAddress());
    }

    @Override
    public void updateEmployee(Employee e) {
        employeeRepository.updateEmployee(e.getEmployeeId(), e.getEmployeeIden(), e.getImage(), e.getBirthday(), e.isGender(), e.getFullname(), e.getIdCard(), e.getEmail(), e.getPhone(), e.getAddress());
    }
}
