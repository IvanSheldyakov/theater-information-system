package com.db.theaterinformationsystem.service;

import com.db.theaterinformationsystem.dto.TicketDTO;
import com.db.theaterinformationsystem.exception.ConflictException;
import com.db.theaterinformationsystem.exception.ExceptionSupplier;
import com.db.theaterinformationsystem.model.Play;
import com.db.theaterinformationsystem.model.Ticket;
import com.db.theaterinformationsystem.repository.PlayRepository;
import com.db.theaterinformationsystem.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final PlayRepository playRepository;

    @Transactional
    public Long create(TicketDTO dto) {
        Play play = playRepository.findById(dto.getId()).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        Ticket ticket = new Ticket();
        ticket.setId(dto.getId());
        ticket.setPlay(play);
        ticket.setCost(dto.getCost());
        ticket.setRow(dto.getRow());
        ticket.setPlace(dto.getPlace());
        Ticket created = ticketRepository.save(ticket);
        return created.getId();
    }

    @Transactional
    public Long buy(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        if (ticket.getBuyDate() != null) {
            throw new ConflictException("Билет уже куплен");
        }
        ticket.setBuyDate(LocalDate.now());
        return ticket.getId();
    }

    public List<TicketDTO> findAllByPlayId(Long playId) {
        Play play = playRepository.findById(playId).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        List<Ticket> tickets = ticketRepository.findAllByPlay(play);
        return tickets.stream().map(ticket ->
        {
            TicketDTO dto = new TicketDTO();
            dto.setPlayId(playId);
            dto.setCost(ticket.getCost());
            dto.setRow(ticket.getRow());
            dto.setPlace(ticket.getPlace());
            dto.setId(ticket.getId());
            return dto;
        }).collect(Collectors.toList());
    }

    public Long countAllSoldTickets() {
        return ticketRepository.findAll().stream().filter(ticket -> ticket.getBuyDate() != null).count();
    }

    public Long countAllUnsoldTickets() {
        return ticketRepository.findAll().stream().filter(ticket -> ticket.getBuyDate() == null).count();
    }

    public Long countAllUnsoldTicketsForPremiers() {
        return ticketRepository.findTicketsForPremieres().stream().filter(ticket -> ticket.getBuyDate() == null).count();
    }

    public Long countUnsoldTicketsForPlay(Long playId) {
        Play play = playRepository.findById(playId).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        return ticketRepository.findAllByPlay(play).stream().filter(ticket -> ticket.getBuyDate() == null).count();
    }

    public BigDecimal sumCostByPlayId(Long playId) {
        Play play = playRepository.findById(playId).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        return ticketRepository.findAllByPlay(play).stream()
                .filter(ticket -> ticket.getBuyDate() != null)
                .map(Ticket::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal sumCostInPeriod(LocalDate start, LocalDate end) {
        return ticketRepository.sumCostInPeriod(start, end).stream()
                .filter(ticket -> ticket.getBuyDate() != null)
                .map(Ticket::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
