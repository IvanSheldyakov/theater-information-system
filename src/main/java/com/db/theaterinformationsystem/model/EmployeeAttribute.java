package com.db.theaterinformationsystem.model;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "theatre", name = "employee_attribute")
@IdClass(EmployeeAttribute.EmployeeAttributeId.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAttribute {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee", nullable = false)
    private Employee employee;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attribute", nullable = false)
    private Attribute attribute;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "value", nullable = false)
    private AttributeValue value;

    @Data
    public static class EmployeeAttributeId implements Serializable {

        private Long employee;
        private Long attribute;
        private Long value;

    }

    @Override
    public String toString() {
        return "EmployeeAttribute{" +
                "attribute=" + attribute.getAttribute() +
                ", value=" + value.getValue() +
                '}';
    }
}


