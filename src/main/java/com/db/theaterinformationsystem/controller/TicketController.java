package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/api")
public class TicketController {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @GetMapping("/tickets/count")
    public Long countAllSoldTickets() {
        return ticketRepository.countAllSoldTickets();
    }

    @GetMapping("/tickets/play/count")
    public Long countTicketsByPlayId(@RequestParam("playId") Long playId) {
        return ticketRepository.countTicketsByPlayId(playId);
    }

    @GetMapping("/tickets/premieres/count")
    public Long countTicketsForPremieres() {
        return ticketRepository.countTicketsForPremieres();
    }

    @GetMapping("/tickets/period/count")
    public Long countTicketsSoldInPeriod(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                         @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ticketRepository.countTicketsSoldInPeriod(startDate, endDate);
    }

    @GetMapping("/tickets/play/sum")
    public BigDecimal sumCostByPlayId(@RequestParam("playId") Long playId) {
        return ticketRepository.sumCostByPlayId(playId);
    }

    @GetMapping("/tickets/period/sum")
    public BigDecimal sumCostInPeriod(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                      @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ticketRepository.sumCostInPeriod(startDate, endDate);
    }
}