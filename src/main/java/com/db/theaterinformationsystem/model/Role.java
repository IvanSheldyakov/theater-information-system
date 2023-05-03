package com.db.theaterinformationsystem.model;

import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(schema = "theatre", name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "actor")
    private Actor actor;

    @Column(name = "main", nullable = false)
    private Boolean main;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "backup")
    private Actor backup;

}