package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.dto.EmployeeAttributeDTO;
import com.db.theaterinformationsystem.repository.EmployeeRepository;
import com.db.theaterinformationsystem.service.AttributeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Tag(name = "Работники")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final AttributeService attributeService;

    @GetMapping("/employees/sex")
    @Operation(description = "Найти работников по полу")
    public List<Map<String, String>> findEmployeesBySex(@RequestParam("sex") String sex) {
        return employeeRepository.findEmployeesBySex(sex);
    }

    @GetMapping("/employees/birthYear")
    @Operation(description = "Найти работников по году рождения")
    public List<Map<String, String>> findEmployeesByBirthYear(@RequestParam("birthYear") Integer birthYear) {
        return employeeRepository.findEmployeesByBirthYear(birthYear);
    }

    @GetMapping("/employees/age")
    @Operation(description = "Найти работников по возрасту")
    public List<Map<String, String>> findEmployeesByAge(@RequestParam("age") String age) {
        return employeeRepository.findEmployeesByAge(age);
    }

    @Operation(description = "Найти работников по наличию детей")
    @GetMapping("/employees/children")
    public List<Map<String, String>> findEmployeesByChildren(@RequestParam("hasChildren") Boolean hasChildren) {
        return employeeRepository.findEmployeesByChildren(hasChildren);
    }

    @Operation(description = "Найти работников по кол-ву детей")
    @GetMapping("/employees/childrenNumber")
    public List<Map<String, String>> findEmployeesByChildrenNumber(@RequestParam("childrenNumber") Integer childrenNumber) {
        return employeeRepository.findEmployeesByChildrenNumber(childrenNumber);
    }

    @Operation(description = "Найти работников по зп")
    @GetMapping("/employees/payment")
    public List<Map<String, String>> findEmployeesByPayment(@RequestParam("payment") String payment) {
        return employeeRepository.findEmployeesByPayment(new BigDecimal(payment));
    }

    @Operation(description = "Список работников")
    @GetMapping("/employees")
    public List<Map<String, String>> findAllEmployees() {
        return employeeRepository.findAllEmployees();
    }

    @Operation(description = "Кол-во работников")
    @GetMapping("/employees/count")
    public long countEmployeesByStanding(@RequestParam("standing") String standing) {
        return employeeRepository.countEmployeesByStanding(standing);
    }

    @Operation(description = "Найти работников по стажу")
    @GetMapping("/employees/standing")
    public List<Map<String, String>> findEmployeesByStanding(@RequestParam("standing") String standing) {
        return employeeRepository.findEmployeesByStanding(standing);
    }

    @PostMapping("/employees/add/attribute")
    public ResponseEntity<HttpStatus> addAttribute(@RequestBody EmployeeAttributeDTO dto) {
        attributeService.addAttribute(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
