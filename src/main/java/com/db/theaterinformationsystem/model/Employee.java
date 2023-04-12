package com.db.theaterinformationsystem.model;

import lombok.*;

import jakarta.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employee", schema = "theatre")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "full_name", nullable = false)
    private FullName fullName;

    @Column(name = "standing", nullable = false)
    private Integer standing;

    @Column(name = "birth_year", nullable = false)
    private Integer birthYear;

    @Column(name = "children", nullable = false)
    private Boolean children;

    @Column(name = "children_number", nullable = false)
    private Integer childrenNumber;

    @Column(name = "payment", nullable = false)
    private Double payment;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;


}