package com.db.theaterinformationsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(schema = "theatre", name = "season_ticket")
@Getter
@Setter
public class SeasonTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre")
    private Genre genre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author")
    private Author author;

    @OneToMany(mappedBy = "seasonTicket", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    @Column(name = "buy_date")
    private LocalDate buyDate;
}
