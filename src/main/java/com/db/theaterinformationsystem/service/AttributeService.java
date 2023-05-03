package com.db.theaterinformationsystem.service;

import com.db.theaterinformationsystem.dto.EmployeeAttributeDTO;
import com.db.theaterinformationsystem.model.Attribute;
import com.db.theaterinformationsystem.model.AttributeValue;
import com.db.theaterinformationsystem.model.Employee;
import com.db.theaterinformationsystem.model.EmployeeAttribute;
import com.db.theaterinformationsystem.repository.AttributeRepository;
import com.db.theaterinformationsystem.repository.AttributeValueRepository;
import com.db.theaterinformationsystem.repository.EmployeeAttributeRepository;
import com.db.theaterinformationsystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttributeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeAttributeRepository employeeAttributeRepository;
    private final AttributeRepository attributeRepository;
    private final AttributeValueRepository attributeValueRepository;

    @Transactional
    public void addAttribute(EmployeeAttributeDTO dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId()).orElseThrow();
        Attribute attribute = getAttribute(dto.getName());
        AttributeValue attributeValue = getAttributeValue(dto.getValue(), attribute);
        Optional<EmployeeAttribute> employeeAttributeOptional = employeeAttributeRepository.findByAttributeAndEmployeeAndValue(attribute, employee, attributeValue);
        if (employeeAttributeOptional.isPresent()) {
            throw new RuntimeException();
        } else {
            employeeAttributeRepository.save(new EmployeeAttribute(employee, attribute, attributeValue));
        }
    }

    private Attribute getAttribute(String name) {
        Optional<Attribute> attributeOptional = attributeRepository.findByAttribute(name);
        return attributeOptional.orElseGet(() -> attributeRepository.save(new Attribute(name)));
    }

    private AttributeValue getAttributeValue(String value, Attribute attribute) {
        Optional<AttributeValue> attributeValueOptional = attributeValueRepository.findByAttributeAndValue(attribute, value);
        return attributeValueOptional.orElseGet(() -> attributeValueRepository.save(new AttributeValue(value, attribute)));
    }

}
