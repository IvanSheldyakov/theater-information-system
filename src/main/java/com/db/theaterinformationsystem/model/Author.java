package com.db.theaterinformationsystem.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(schema = "theatre", name = "author")
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "full_name", nullable = false)
    private FullName fullName;

    @Column(name = "century", nullable = false)
    private Integer century;

    @Column(name = "country", nullable = false)
    private String country;

}
