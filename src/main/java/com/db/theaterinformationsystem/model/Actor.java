package com.db.theaterinformationsystem.model;

import lombok.*;

import javax.persistence.*;

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

    @OneToOne
    @JoinColumn(name = "employee", nullable = false, unique = true)
    private Employee employee;

    @Column(name = "honored_artist", nullable = false)
    private Boolean honoredArtist;

    @Column(name = "national_artist", nullable = false)
    private Boolean nationalArtist;

    @Column(name = "student", nullable = false)
    private Boolean student;

}