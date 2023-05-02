package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.Musician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MusicianRepository extends JpaRepository<Musician, Long> {

    @Query("SELECT COUNT(m) FROM Musician m WHERE m.employee.standing = :standing")
    long countMusiciansWithStanding(@Param("standing") String standing);

    @Query("SELECT f.name AS name, f.surname AS surname, COALESCE(f.patronymic, '') AS patronymic " +
            "FROM Musician m JOIN m.employee e JOIN e.fullName f")
    List<Map<String,String>> findAllMusicians();

    @Query("SELECT f.name AS name, f.surname AS surname, COALESCE(f.patronymic, '') AS patronymic " +
            "FROM Musician m JOIN m.employee e JOIN e.fullName f " +
            "WHERE e.standing = :standing")
    List<Map<String,String>> findMusiciansWithStanding(@Param("standing") String standing);

    Musician save(Musician musician);
}
