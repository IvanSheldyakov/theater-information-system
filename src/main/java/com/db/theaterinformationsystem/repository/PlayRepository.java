package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.Play;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface PlayRepository extends JpaRepository<Play, Long> {

    // Все спектакли театра
    @Query("SELECT p.name FROM Play p")
    List<String> findAllPlays();

    @Query("SELECT p.name FROM Play p JOIN p.genre g WHERE g.name = :genreName")
    List<String> findPlaysByGenreName(@Param("genreName") String genreName);

    @Query("SELECT p.name FROM Play p JOIN p.author a WHERE a.country = :country")
    List<String> findPlaysByAuthorCountry(@Param("country") String country);

    @Query("SELECT p.name FROM Play p WHERE p.createCentury = :century")
    List<String> findPlaysByCreateCentury(@Param("century") Integer century);

    @Query("SELECT p.name FROM Play p WHERE p.premiere BETWEEN :startDate AND :endDate")
    List<String> findPlaysByPremierePeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


    @Query("SELECT p.name FROM Play p WHERE p.premiere < CURRENT_DATE")
    List<String> findAllBeforeCurrentDate();


    @Query("SELECT COUNT(p) FROM Play p WHERE p.premiere < CURRENT_DATE")
    long countAllBeforeCurrentDate();

    @Query("SELECT p.name FROM Play p WHERE p.premiere BETWEEN :startDate AND :endDate")
    List<String> findAllBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT p.premiere FROM Play p WHERE p.id = :playId")
    LocalDate findPremiereDateByPlayId(@Param("playId") Long playId);

    Play save(Play play);

}