package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.dto.TicketDTO;
import com.db.theaterinformationsystem.repository.TicketRepository;
import com.db.theaterinformationsystem.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TicketController {

    private final TicketRepository ticketRepository;
    private final TicketService ticketService;

    @GetMapping("/tickets/sold/count")
    @Operation(description = "Все проданные билеты")
    public Long countAllSoldTickets() {
        return ticketService.countAllSoldTickets();
    }

    @GetMapping("/tickets/unsold/count")
    @Operation(description = "Кол-во свободных мест на все спектакли")
    public Long countAllUnsoldTickets() {
        return ticketService.countAllUnsoldTickets();
    }

    @GetMapping("/tickets/play/count")
    @Operation(description = "Свободные билеты на спектакль")
    public Long countTicketsByPlayId(@RequestParam("playId") Long playId) {
        return ticketService.countUnsoldTicketsForPlay(playId);
    }

    @GetMapping("/tickets/premieres/count")
    @Operation(description = "Кол-во билетов на премьеры")
    public Long countTicketsForPremieres() {
        return ticketService.countAllUnsoldTicketsForPremiers();
    }

    @GetMapping("/tickets/period/count")
    @Operation(description = "Кол-во проданных билетов за период")
    public Long countTicketsSoldInPeriod(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                         @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ticketRepository.countTicketsSoldInPeriod(startDate, endDate);
    }

    @GetMapping("/tickets/play/sum")
    @Operation(description = "Сумма денег за указанный спектакль")
    public BigDecimal sumCostByPlayId(@RequestParam("playId") Long playId) {
        return ticketService.sumCostByPlayId(playId);
    }

    @GetMapping("/tickets/period/sum")
    @Operation(description = "Сумма за определеное время")
    public BigDecimal sumCostInPeriod(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                      @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ticketService.sumCostInPeriod(startDate, endDate);
    }

    @PostMapping("/tickets/create")
    public ResponseEntity<Long> create(@RequestBody TicketDTO ticket) {
        return new ResponseEntity<>(ticketService.create(ticket), HttpStatus.CREATED);
    }

    @PostMapping("/tickets/buy")
    public ResponseEntity<Long> buy(@RequestParam Long id) {
        return new ResponseEntity<>(ticketService.buy(id), HttpStatus.OK);
    }

    @GetMapping("/tickets/find/by/play")
    public ResponseEntity<List<TicketDTO>> findAllByPlayId(@RequestParam Long playId) {
        return new ResponseEntity<>(ticketService.findAllByPlayId(playId), HttpStatus.OK);
    }

}