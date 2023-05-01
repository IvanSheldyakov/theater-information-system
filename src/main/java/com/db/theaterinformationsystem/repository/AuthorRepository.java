package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT f.name AS name, f.surname AS surname, COALESCE(f.patronymic, '') AS patronymic " +
            "FROM Author a JOIN a.fullName f JOIN Play p ON p.author = a " +
            "WHERE p.premiere < CURRENT_DATE " +
            "GROUP BY f.name, f.surname, f.patronymic")
    List<Map<String, String>> findPerformedPlayAuthors();

    @Query("SELECT f.name AS name, f.surname AS surname, COALESCE(f.patronymic, '') AS patronymic " +
            "FROM Author a JOIN a.fullName f " +
            "WHERE a.century = :century")
    List<Map<String, String>> findAuthorsByCentury(@Param("century") String century);

    @Query("SELECT f.name AS name, f.surname AS surname, COALESCE(f.patronymic, '') AS patronymic " +
            "FROM Author a JOIN a.fullName f " +
            "WHERE a.country = :country")
    List<Map<String, String>> findAuthorsByCountry(@Param("country") String country);

    @Query("SELECT f.name AS name, f.surname AS surname, COALESCE(f.patronymic, '') AS patronymic " +
            "FROM Author a JOIN a.fullName f JOIN Play p ON p.author = a JOIN p.genre g " +
            "WHERE g.name = :genre AND p.premiere < CURRENT_DATE " +
            "GROUP BY f.name, f.surname, f.patronymic")
    List<Map<String, String>> findAuthorsByPerformedPlayGenre(@Param("genre") String genre);

    @Query("SELECT f.name AS name, f.surname AS surname, COALESCE(f.patronymic, '') AS patronymic " +
            "FROM Author a JOIN a.fullName f JOIN Play p ON p.author = a " +
            "WHERE p.premiere BETWEEN :startDate AND :endDate " +
            "GROUP BY f.name, f.surname, f.patronymic")
    List<Map<String, String>> findAuthorsByPlayPremierePeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
