package com.db.theaterinformationsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(schema = "theatre", name = "attribute_value")
@Getter
@Setter
@NoArgsConstructor
public class AttributeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value", nullable = false)
    private String value;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attribute", nullable = false)
    private Attribute attribute;

    public AttributeValue(String value, Attribute attribute) {
        this.value = value;
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return "AttributeValue{" +
                "value='" + value + '\'' +
                ", attribute=" + attribute.getAttribute() +
                '}';
    }
}
