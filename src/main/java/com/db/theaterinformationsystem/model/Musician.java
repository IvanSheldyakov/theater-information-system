package com.db.theaterinformationsystem.model;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Table(schema = "theatre", name = "musician")
@Data
public class Musician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee", nullable = false, unique = true)
    private Employee employee;

}

