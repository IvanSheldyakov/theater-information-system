package com.db.theaterinformationsystem.model;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "role", schema = "theatre")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "actor")
    private Actor actor;

    @Column(name = "primary", nullable = false)
    private Boolean primary;

    @ManyToOne
    @JoinColumn(name = "backup")
    private Actor backup;

}