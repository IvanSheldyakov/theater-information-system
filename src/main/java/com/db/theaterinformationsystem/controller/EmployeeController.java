package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @GetMapping("/employees/sex")
    public List<Map<String, String>> findEmployeesBySex(@RequestParam("sex") String sex) {
        return employeeRepository.findEmployeesBySex(sex);
    }

    @GetMapping("/employees/birthYear")
    public List<Map<String, String>> findEmployeesByBirthYear(@RequestParam("birthYear") Integer birthYear) {
        return employeeRepository.findEmployeesByBirthYear(birthYear);
    }

    @GetMapping("/employees/age")
    public List<Map<String, String>> findEmployeesByAge(@RequestParam("age") String age) {
        return employeeRepository.findEmployeesByAge(age);
    }

    @GetMapping("/employees/children")
    public List<Map<String, String>> findEmployeesByChildren(@RequestParam("hasChildren") Boolean hasChildren) {
        return employeeRepository.findEmployeesByChildren(hasChildren);
    }

    @GetMapping("/employees/childrenNumber")
    public List<Map<String, String>> findEmployeesByChildrenNumber(@RequestParam("childrenNumber") Integer childrenNumber) {
        return employeeRepository.findEmployeesByChildrenNumber(childrenNumber);
    }

    @GetMapping("/employees/payment")
    public List<Map<String, String>> findEmployeesByPayment(@RequestParam("payment") String payment) {
        return employeeRepository.findEmployeesByPayment(payment);
    }

    @GetMapping("/employees")
    public List<Map<String, String>> findAllEmployees() {
        return employeeRepository.findAllEmployees();
    }

    @GetMapping("/employees/count")
    public long countEmployeesByStanding(@RequestParam("standing") String standing) {
        return employeeRepository.countEmployeesByStanding(standing);
    }

    @GetMapping("/employees/standing")
    public List<Map<String, String>> findEmployeesByStanding(@RequestParam("standing") String standing) {
        return employeeRepository.findEmployeesByStanding(standing);
    }


}
