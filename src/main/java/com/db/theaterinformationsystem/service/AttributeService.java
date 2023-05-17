package com.db.theaterinformationsystem.service;

import com.db.theaterinformationsystem.dto.EmployeeAttributeDTO;
import com.db.theaterinformationsystem.dto.RoleAttributeDTO;
import com.db.theaterinformationsystem.exception.ConflictException;
import com.db.theaterinformationsystem.exception.ExceptionSupplier;
import com.db.theaterinformationsystem.model.*;
import com.db.theaterinformationsystem.repository.*;
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
    private final RoleAttributeRepository roleAttributeRepository;
    private final RoleRepository roleRepository;

    @Transactional
    public void addAttribute(EmployeeAttributeDTO dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId()).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        Attribute attribute = getAttribute(dto.getName());
        AttributeValue attributeValue = getAttributeValue(dto.getValue(), attribute);
        Optional<EmployeeAttribute> employeeAttributeOptional = employeeAttributeRepository.findByAttributeAndEmployeeAndValue(attribute, employee, attributeValue);
        if (employeeAttributeOptional.isPresent()) {
            throw new ConflictException("Уже существует");
        } else {
            employeeAttributeRepository.save(new EmployeeAttribute(employee, attribute, attributeValue));
        }
    }

    public String findAttributeValue(Employee employee, String attributeName) {
        Attribute attribute = attributeRepository.findByAttribute(attributeName).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        EmployeeAttribute employeeAttribute = employeeAttributeRepository.findByAttributeAndEmployee(attribute, employee).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        return employeeAttribute.getValue().getValue();
    }

    public String findAttributeValue(Role role, String attributeName) {
        Attribute attribute = attributeRepository.findByAttribute(attributeName).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        RoleAttribute roleAttribute = roleAttributeRepository.findByAttributeAndRole(attribute, role).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        return roleAttribute.getValue().getValue();
    }

    public void setSexAttribute(Employee employee, String value) {
        EmployeeAttributeDTO sexAttribute = new EmployeeAttributeDTO();
        sexAttribute.setEmployeeId(employee.getId());
        sexAttribute.setName("пол");
        sexAttribute.setValue(value);
        addAttribute(sexAttribute);
    }

    public void setAgeAttribute(Employee employee, String value) {
        EmployeeAttributeDTO ageAttribute = new EmployeeAttributeDTO();
        ageAttribute.setEmployeeId(employee.getId());
        ageAttribute.setName("возраст");
        ageAttribute.setValue(value);
        addAttribute(ageAttribute);
    }

    public void updateSexAttribute(Employee employee, String value) {
        EmployeeAttributeDTO sexAttribute = new EmployeeAttributeDTO();
        sexAttribute.setEmployeeId(employee.getId());
        sexAttribute.setName("пол");
        sexAttribute.setValue(value);
        updateAttribute(sexAttribute);
    }

    public void updateAgeAttribute(Employee employee, String value) {
        EmployeeAttributeDTO ageAttribute = new EmployeeAttributeDTO();
        ageAttribute.setEmployeeId(employee.getId());
        ageAttribute.setName("возраст");
        ageAttribute.setValue(value);
        updateAttribute(ageAttribute);
    }


    @Transactional
    public void addAttribute(RoleAttributeDTO dto) {
        Role role = roleRepository.findById(dto.getRoleId()).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        Attribute attribute = getAttribute(dto.getName());
        AttributeValue attributeValue = getAttributeValue(dto.getValue(), attribute);
        Optional<RoleAttribute> roleAttributeOptional = roleAttributeRepository.findByAttributeAndRoleAndValue(attribute, role, attributeValue);
        if (roleAttributeOptional.isPresent()) {
            throw new ConflictException("Уже существует");
        } else {
            roleAttributeRepository.save(new RoleAttribute(role, attribute, attributeValue));
        }
    }

    public void setDesAttribute(Role role, String value) {
        RoleAttributeDTO desAttribute = new RoleAttributeDTO();
        desAttribute.setRoleId(role.getId());
        desAttribute.setName("описание роли");
        desAttribute.setValue(value);
        addAttribute(desAttribute);
    }

    public void updateDesAttribute(Role role, String value) {
        RoleAttributeDTO desAttribute = new RoleAttributeDTO();
        desAttribute.setRoleId(role.getId());
        desAttribute.setName("описание роли");
        desAttribute.setValue(value);
        updateAttribute(desAttribute);
    }

    @Transactional
    public void updateAttribute(RoleAttributeDTO dto) {
        Role role = roleRepository.findById(dto.getRoleId()).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        Attribute attribute = attributeRepository.findByAttribute(dto.getName()).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        RoleAttribute roleAttribute = roleAttributeRepository.findByAttributeAndRole(attribute, role).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        roleAttribute.getValue().setValue(dto.getValue());

    }

    @Transactional
    public void updateAttribute(EmployeeAttributeDTO dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId()).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        Attribute attribute = attributeRepository.findByAttribute(dto.getName()).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        EmployeeAttribute employeeAttribute = employeeAttributeRepository.findByAttributeAndEmployee(attribute, employee).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        employeeAttribute.getValue().setValue(dto.getValue());

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
