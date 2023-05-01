package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Long countTicketsSoldInPeriod(@RequestParam("startDate") LocalDate startDate,
                                         @RequestParam("endDate") LocalDate endDate) {
        return ticketRepository.countTicketsSoldInPeriod(startDate, endDate);
    }

    @GetMapping("/tickets/play/sum")
    public Long sumCostByPlayId(@RequestParam("playId") Long playId) {
        return ticketRepository.sumCostByPlayId(playId);
    }

    @GetMapping("/tickets/period/sum")
    public Long sumCostInPeriod(@RequestParam("startDate") LocalDate startDate,
                                @RequestParam("endDate") LocalDate endDate) {
        return ticketRepository.sumCostInPeriod(startDate, endDate);
    }
}