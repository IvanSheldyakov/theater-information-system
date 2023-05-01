package com.db.theaterinformationsystem.model;

import lombok.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(schema = "theatre", name = "play")
@Getter
@Setter
public class Play {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "genre", nullable = false)
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "audience", nullable = false)
    private Audience audience;

    @ManyToOne
    @JoinColumn(name = "author", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "director_producer", nullable = false)
    private Producer directorProducer;

    @ManyToOne
    @JoinColumn(name = "art_producer", nullable = false)
    private Producer artProducer;

    @ManyToOne
    @JoinColumn(name = "conductor_producer", nullable = false)
    private Producer conductorProducer;

    @Column(name = "premiere", nullable = false)
    private LocalDate premiere;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "create_century", nullable = false)
    private Integer createCentury;

    @Column(name = "places", nullable = false)
    private Integer places;

}
