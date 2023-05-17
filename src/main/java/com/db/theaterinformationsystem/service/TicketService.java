package com.db.theaterinformationsystem.service;

import com.db.theaterinformationsystem.dto.SeasonTicketDTO;
import com.db.theaterinformationsystem.dto.TicketDTO;
import com.db.theaterinformationsystem.exception.ConflictException;
import com.db.theaterinformationsystem.exception.DataNotFoundException;
import com.db.theaterinformationsystem.exception.ExceptionSupplier;
import com.db.theaterinformationsystem.model.*;
import com.db.theaterinformationsystem.repository.*;
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
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    private final SeasonTicketRepository seasonTicketRepository;

    @Transactional
    public Long create(TicketDTO dto) {
        Play play = playRepository.findById(dto.getPlayId()).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        Ticket ticket = new Ticket();
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
        if (ticket.getBuyDate() != null || ticket.getSeasonTicket() != null) {
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

    @Transactional
    public Long create(SeasonTicketDTO dto) {
        if (dto.getTicketIds().isEmpty()) {
            throw new DataNotFoundException("Нет билетов");
        }
        if (dto.getAuthorId() != null && dto.getGenreId() != null) {
            throw new ConflictException("Выбери либо автора, либо жанр");
        } else if (dto.getGenreId() != null) {
            Genre genre = genreRepository.findById(dto.getGenreId()).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
            SeasonTicket seasonTicket = new SeasonTicket();
            seasonTicket.setGenre(genre);
            List<Ticket> tickets = getTickets(dto.getTicketIds());
            long count = tickets.stream().filter(ticket -> ticket.getBuyDate() == null).map(Ticket::getPlay).filter(play -> play.getGenre().equals(genre)).count();
            if (count != tickets.size()) {
                throw new ConflictException("Не все билеты соответствуют жанру или уже куплены");
            }
            seasonTicket.setTickets(tickets);
            seasonTicket.setCost(tickets.stream().map(Ticket::getCost).reduce(BigDecimal.ZERO, BigDecimal::add).multiply(BigDecimal.valueOf(0.9)));
            return seasonTicketRepository.save(seasonTicket).getId();

        } else if (dto.getAuthorId() != null) {
            Author author = authorRepository.findById(dto.getAuthorId()).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
            SeasonTicket seasonTicket = new SeasonTicket();
            seasonTicket.setAuthor(author);
            List<Ticket> tickets = getTickets(dto.getTicketIds());
            long count = tickets.stream().filter(ticket -> ticket.getBuyDate() == null).map(Ticket::getPlay).filter(play -> play.getAuthor().equals(author)).count();
            if (count != tickets.size()) {
                throw new ConflictException("Не все билеты соответствуют автору");
            }
            seasonTicket.setTickets(tickets);
            seasonTicket.setCost(tickets.stream().map(Ticket::getCost).reduce(BigDecimal.ZERO, BigDecimal::add).multiply(BigDecimal.valueOf(0.9)));
            return seasonTicketRepository.save(seasonTicket).getId();
        } else {
            throw new DataNotFoundException("Выбери либо автора, либо жанр");
        }
    }

    @Transactional
    public void buySeasonTicket(Long id) {
        SeasonTicket seasonTicket = seasonTicketRepository.findById(id).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        if (seasonTicket.getBuyDate() != null) {
            throw new ConflictException("Уже куплен");
        }
        LocalDate date = LocalDate.now();
        seasonTicket.getTickets().forEach(ticket -> ticket.setBuyDate(date));
        seasonTicket.setBuyDate(date);

    }

    private List<Ticket> getTickets(List<Long> ids) {
        return ids.stream()
                .map(id -> ticketRepository.findById(id).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND))
                .collect(Collectors.toList());
    }
}
