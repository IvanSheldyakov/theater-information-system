package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {

    @Query("SELECT f.name AS name, f.surname AS surname, COALESCE(f.patronymic, '') AS patronymic " +
            "FROM FullName f " +
            "WHERE EXISTS (SELECT 1 FROM Actor a JOIN PlayActor pa ON a = pa.actor " +
            "JOIN Play p ON pa.play = p JOIN Tour t ON p = t.play " +
            "WHERE a.employee.fullName = f AND t.startTour > :startDate AND t.endTour < :endDate) " +
            "OR EXISTS (SELECT 1 FROM Producer pro JOIN Play p ON pro = p.directorProducer OR pro = p.artProducer OR pro = p.conductorProducer " +
            "JOIN Tour t ON p = t.play " +
            "WHERE pro.employee.fullName = f AND t.startTour > :startDate AND t.endTour < :endDate) " +
            "GROUP BY f.name, f.surname, f.patronymic")
    List<Map<String, String>> findActorsAndProducersInTourByPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT f.name AS name, f.surname AS surname, COALESCE(f.patronymic, '') AS patronymic " +
            "FROM FullName f " +
            "WHERE EXISTS (SELECT 1 FROM Actor a JOIN PlayActor pa ON a = pa.actor " +
            "JOIN Play p ON pa.play = p JOIN Tour t ON p = t.play " +
            "WHERE a.employee.fullName = f AND t.startTour = :startDate AND p.id = :playId) " +
            "OR EXISTS (SELECT 1 FROM Producer pro JOIN Play p ON pro = p.directorProducer OR pro = p.artProducer OR pro = p.conductorProducer " +
            "JOIN Tour t ON p = t.play " +
            "WHERE pro.employee.fullName = f AND t.startTour = :startDate AND p.id = :playId) " +
            "GROUP BY f.name, f.surname, f.patronymic")
    List<Map<String, String>> findActorsAndProducersInTourByPlayAndTime(@Param("startDate") LocalDate startDate, @Param("playId") Long playId);
}
