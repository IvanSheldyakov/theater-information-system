package com.db.theaterinformationsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(schema = "theatre", name = "tour")
@Getter
@Setter
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_tour", nullable = false)
    private LocalDate startTour;

    @Column(name = "end_tour", nullable = false)
    private LocalDate endTour;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "play", nullable = false)
    private Play play;

}
