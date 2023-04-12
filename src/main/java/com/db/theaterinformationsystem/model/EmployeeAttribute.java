package com.db.theaterinformationsystem.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "theatre", name = "employee_attribute")
@IdClass(EmployeeAttribute.EmployeeAttributeId.class)
@Getter
@Setter
public class EmployeeAttribute {

    @Id
    @ManyToOne
    @JoinColumn(name = "employee", nullable = false)
    private Employee employee;

    @Id
    @ManyToOne
    @JoinColumn(name = "attribute", nullable = false)
    private Attribute attribute;

    @Id
    @ManyToOne
    @JoinColumn(name = "value", nullable = false)
    private AttributeValue value;

    @Data
    public static class EmployeeAttributeId implements Serializable {

        private Long employee;
        private Long attribute;
        private Long value;

    }
}


