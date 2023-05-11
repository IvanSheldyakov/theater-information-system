package com.db.theaterinformationsystem.model;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(schema = "theatre", name = "ticket")
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    @Column(name = "place", nullable = false)
    private Integer place;

    @Column(name = "row", nullable = false)
    private Integer row;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "play", nullable = false)
    private Play play;

    @Column(name = "buy_date")
    private LocalDate buyDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "season_ticket_id")
    private SeasonTicket seasonTicket;

}
