package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT COUNT(t) FROM Ticket t")
    Long countAllSoldTickets();

    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.play.id = :playId")
    Long countTicketsByPlayId(@Param("playId") Long playId);

    @Query("SELECT COUNT(t) FROM Ticket t JOIN Play p ON t.play = p JOIN Repertoire rep ON p = rep.play WHERE p.premiere = rep.date")
    Long countTicketsForPremieres();

    @Query("SELECT COUNT(t) FROM Ticket t WHERE t.buyDate BETWEEN :startDate AND :endDate")
    Long countTicketsSoldInPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT SUM(t.cost) FROM Ticket t WHERE t.play.id = :playId")
    BigDecimal sumCostByPlayId(@Param("playId") Long playId);

    @Query("SELECT SUM(t.cost) FROM Ticket t WHERE t.buyDate BETWEEN :startDate AND :endDate")
    BigDecimal sumCostInPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
