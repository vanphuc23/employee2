package com.example.be.controller;

import com.example.be.dto.ApiResponse;
import com.example.be.entity.Employee;
import com.example.be.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "**")
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public ResponseEntity<Page<Employee>> findAll(@PageableDefault(page = 0, size = 5) Pageable pageable,
                                                  @RequestParam(required = false, defaultValue = "") String fullname,
                                                  @RequestParam(required = false, defaultValue = "") String phone,
                                                  @RequestParam(required=false,defaultValue = "employeeId") String sort) {
        Sort sort1 = Sort.by(Sort.Direction.ASC, sort);
        Pageable pageableWithSort = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort1);
        Page<Employee> list = employeeService.findAll("%" + fullname + "%", "%" + phone + "%", pageableWithSort);
        if(list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/{ids}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable("ids") String ids) {
        String[] number = ids.split(",");
        List<String> result = new ArrayList<>();
        String mes = null;
        for (int i = 0; i < number.length; i++) {
            if (Integer.parseInt(number[i]) <= 0 ) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                employeeService.deleteEmployee(Integer.parseInt(number[i]));
                result.add(number[i]);
            }
        }
        if (!result.isEmpty()) {
            mes="Employee with ID " + ids + " has been deleted successfully.";
        }
        return new ResponseEntity<>(new ApiResponse(mes, true), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable("id")int id){
        Employee employeeFindId = employeeService.findById(id);
        if(employeeFindId== null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(employeeFindId, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Employee employee) {
        if(employee == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        employeeService.createNewEmployee(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> edit(@RequestBody Employee employee) {
        if(employee == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        employeeService.updateEmployee(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
