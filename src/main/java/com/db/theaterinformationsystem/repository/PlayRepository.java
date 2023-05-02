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

    @Query("SELECT p.name FROM Play p JOIN p.author a JOIN a.fullName f " +
            "WHERE f.name = :authorName AND f.surname = :authorSurname AND f.patronymic = :authorPatronymic")
    List<String> findPlaysByAuthorFullName(@Param("authorName") String authorName,
                                           @Param("authorSurname") String authorSurname,
                                           @Param("authorPatronymic") String authorPatronymic);


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

    @Query("SELECT f.name, f.surname, COALESCE(f.patronymic, '') " +
            "FROM Actor a JOIN PlayActor pa ON a = pa.actor " +
            "JOIN Play p ON pa.play = p JOIN FullName f ON a.employee.fullName = f " +
            "WHERE p.id = :playId")
    List<Map<String, String>> findActorsByPlayId(@Param("playId") Long playId);

    @Query("SELECT f.name, f.surname, COALESCE(f.patronymic, '') " +
            "FROM Actor a JOIN Role r ON a = r.backup " +
            "JOIN PlayRole pr ON r = pr.role JOIN Play p ON pr.play = p " +
            "JOIN FullName f ON a.employee.fullName = f " +
            "WHERE p.id = :playId")
    List<Map<String, String>> findBackupsByPlayId(@Param("playId") Long playId);

    @Query("SELECT f.name, f.surname, COALESCE(f.patronymic, '') " +
            "FROM Producer pro JOIN Play p ON pro = p.directorProducer OR pro = p.artProducer OR pro = p.conductorProducer " +
            "JOIN FullName f ON pro.employee.fullName = f " +
            "WHERE p.id = :playId")
    List<Map<String, String>> findProducersByPlayId(@Param("playId") Long playId);

    @Query("SELECT f.name, f.surname, COALESCE(f.patronymic, '') " +
            "FROM Author a JOIN Play p ON a = p.author " +
            "JOIN FullName f ON a.fullName = f " +
            "WHERE p.premiere < CURRENT_DATE " +
            "GROUP BY f.name, f.surname, f.patronymic")
    List<Map<String, String>> findAuthorsWithPastPremieres();

    @Query("SELECT p.premiere FROM Play p WHERE p.id = :playId")
    LocalDate findPremiereDateByPlayId(@Param("playId") Long playId);

    Play save(Play play);

}