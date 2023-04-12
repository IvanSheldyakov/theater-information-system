package com.db.theaterinformationsystem.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(schema = "theatre", name = "attribute_value")
@Getter
@Setter
public class AttributeValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value", nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(name = "attribute", nullable = false)
    private Attribute attribute;


}
