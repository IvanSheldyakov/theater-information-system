package com.db.theaterinformationsystem.model;

import jakarta.persistence.*;
import lombok.*;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "actor", schema = "theatre")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee", nullable = false, unique = true)
    private Employee employee;

    @Column(name = "honored_artist", nullable = false)
    private Boolean honoredArtist = Boolean.FALSE;

    @Column(name = "national_artist", nullable = false)
    private Boolean nationalArtist = Boolean.FALSE;

    @Column(name = "student", nullable = false)
    private Boolean student = Boolean.FALSE;

}