package com.db.theaterinformationsystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(schema = "theatre", name = "attribute")
@Getter
@Setter
@NoArgsConstructor
public class Attribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "attribute", nullable = false, unique = true)
    private String attribute;

    public Attribute(String attribute) {
        this.attribute = attribute;
    }
}
