package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.Play;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PlayRepository extends JpaRepository<Play, Long> {
    @Query("SELECT p.name FROM Play p WHERE p.genre.name = ?1")
    List<String> findAllByGenre(String genre);

    @Query("SELECT e.fullName.name AS name, e.fullName.surname AS surname, COALESCE(e.fullName.patronymic, '') AS patronymic " +
            "FROM Play p JOIN p.playActors pa JOIN pa.actor a JOIN a.employee e " +
            "WHERE p.id = ?1")
    List<Map<String, String>> findAllActorsByPlayId(Long playId);
}