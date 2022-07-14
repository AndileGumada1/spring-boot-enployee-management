package com.schoolofit.employeemanagementapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.schoolofit.employeemanagementapp.services.EmployeeServiceImplementation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:3000/")
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    private final EmployeeServiceImplementation service;
    private final ObjectMapper mapper;

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) throws JsonProcessingException {
        log.info("Saving a new employee {} :" + mapper.writeValueAsString(employee));
        return service.createEmployee(employee);
    }

    @GetMapping()
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable("id") Long id) {
        boolean deleted = false;
        deleted = service.deleteEmployee(id);
        Map<String, Boolean> response = new HashMap<>();

        response.put("Deleted", deleted);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id,
                                                   @RequestBody Employee employee) throws JsonProcessingException {
        log.info("Updating an employee {} :" + mapper.writeValueAsString(employee));
        employee = service.updateEmployee(id, employee);
        return ResponseEntity.ok(employee);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long id) throws JsonProcessingException {
        Employee employee = service.getEmployeeById(id);
        log.info("Getting an employee by id{} :" + mapper.writeValueAsString(employee));

        return ResponseEntity.ok(employee);
    }

}
