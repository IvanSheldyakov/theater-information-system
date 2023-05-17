package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.Attribute;
import com.db.theaterinformationsystem.model.AttributeValue;
import com.db.theaterinformationsystem.model.Employee;
import com.db.theaterinformationsystem.model.EmployeeAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeAttributeRepository extends JpaRepository<EmployeeAttribute, Long> {

    Optional<EmployeeAttribute> findByAttributeAndEmployeeAndValue(Attribute attribute, Employee employee, AttributeValue value);

    Optional<EmployeeAttribute> findByAttributeAndEmployee(Attribute attribute, Employee employee);
    EmployeeAttribute save(EmployeeAttribute attribute);
}
