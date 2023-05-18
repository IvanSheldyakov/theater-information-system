package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.Repertoire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RepertoireRepository extends JpaRepository<Repertoire, Long> {

    // Список спектаклей в репертуаре
    @Query("SELECT p.name FROM Repertoire r JOIN r.play p")
    List<String> findAllPlaysInRepertoire();

    // Сыгранные спектакли
    @Query("SELECT p.name FROM Repertoire r JOIN r.play p WHERE r.date < CURRENT_DATE")
    List<String> findPastPlaysInRepertoire();

    // Спектакли указанного жанра в репертуаре
    @Query("SELECT p.name FROM Repertoire r JOIN r.play p JOIN p.genre g WHERE g.name = :genreName")
    List<String> findAllPlaysInRepertoireByGenre(@Param("genreName") String genreName);


    // Все спектакли в репертуаре за указанный период
    @Query("SELECT p.name FROM Repertoire r JOIN r.play p WHERE r.date BETWEEN :startDate AND :endDate")
    List<String> findAllPlaysInRepertoireBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


    @Query("SELECT SUM(p.places) - SUM((SELECT COUNT(t) FROM Ticket t WHERE t.play.id = p.id)) FROM Play p")
    Long countFreeSeatsForAllPlays();

    @Query("SELECT (p.places - COUNT(t.id)) FROM Play p LEFT JOIN Ticket t ON t.play.id = p.id WHERE p.id = :playId GROUP BY p.places")
    Long countFreeSeatsForSpecificPlay(@Param("playId") Long playId);

    @Query("SELECT COUNT(t) FROM Ticket t, Repertoire rep WHERE t.play.premiere = rep.date AND t.play.id = rep.play.id")
    Long countSoldTicketsForPremieres();

    @Query("SELECT SUM(p.places) FROM Play p, Repertoire rep WHERE p.id = rep.play.id AND p.premiere = rep.date")
    Long countTotalSeatsForPremieres();



}
