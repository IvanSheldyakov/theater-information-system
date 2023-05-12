package com.db.theaterinformationsystem.model;

import com.db.theaterinformationsystem.converters.PeriodToStringConverter;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Period;


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

    @Column(columnDefinition = "text not null", name = "standing")
    private String standing;

    @Column(name = "birth_year", nullable = false, columnDefinition = "integer default 0")
    private Integer birthYear;

    @Column(name = "children", nullable = false, columnDefinition = "bool default false")
    private Boolean children;

    @Column(name = "children_number", nullable = false)
    private Integer childrenNumber;

    @Column(precision = 19, scale = 4, nullable = false)
    private BigDecimal payment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category")
    private Category category;

}