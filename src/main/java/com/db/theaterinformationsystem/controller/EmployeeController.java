package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeRepository repository;

    @GetMapping
    public ResponseEntity<?> get() {
        return new ResponseEntity<>(repository.findAllEmployees(), HttpStatus.OK);
    }
}
