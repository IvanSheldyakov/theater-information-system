package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.Actor;
import com.db.theaterinformationsystem.model.ActorProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    long count();

    @Query("SELECT COUNT(a) FROM Actor a WHERE a.employee.standing = :standing")
    long countActorsWithStanding(@Param("standing") String standing);

    @Query("SELECT f.name AS name, f.surname AS surname, COALESCE(f.patronymic, '') AS patronymic " +
            "FROM Actor a JOIN a.employee e JOIN e.fullName f " +
            "WHERE e.standing = :standing")
    List<Map<String, String>> findActorsWithStanding(@Param("standing") String standing);

    @Query("SELECT e.fullName.name AS name, e.fullName.surname AS surname, COALESCE(e.fullName.patronymic, '') AS patronymic " +
            "FROM Actor a JOIN a.employee e")
    List<Map<String, String>> findAllActors();

    @Query("SELECT e.fullName.name AS name, e.fullName.surname AS surname, COALESCE(e.fullName.patronymic, '') AS patronymic " +
            "FROM Actor a JOIN a.employee e " +
            "WHERE a.honoredArtist = true OR a.nationalArtist = true")
    List<Map<String, String>> findAllActorsWithTitles();

    @Query("SELECT e.fullName.name AS name, e.fullName.surname AS surname, COALESCE(e.fullName.patronymic, '') AS patronymic " +
            "FROM Actor a JOIN a.employee e JOIN ActorContest ac ON a.id = ac.actor.id JOIN ac.contest c " +
            "WHERE (a.honoredArtist = true OR a.nationalArtist = true) AND ac.winner = true AND c.date BETWEEN ?1 AND ?2")
    List<Map<String, String>> findAllActorsWithTitlesInPeriod(LocalDate startDate, LocalDate endDate);

    @Query("SELECT e.fullName.name AS name, e.fullName.surname AS surname, COALESCE(e.fullName.patronymic, '') AS patronymic " +
            "FROM Actor a JOIN a.employee e JOIN ActorContest ac ON a.id = ac.actor.id JOIN ac.contest c " +
            "WHERE (a.honoredArtist = true OR a.nationalArtist = true) AND ac.winner = true AND c.name = ?1")
    List<Map<String, String>> findAllActorsWithTitlesInContest(String contestName);

    @Query("SELECT e.fullName.name AS name, e.fullName.surname AS surname, COALESCE(e.fullName.patronymic, '') AS patronymic " +
            "FROM Actor a JOIN a.employee e JOIN EmployeeAttribute ea ON e.id = ea.employee.id JOIN ea.attribute attr JOIN ea.value av " +
            "WHERE attr.attribute = 'пол' AND av.value = ?1")
    List<Map<String, String>> findAllActorsByGender(String gender);

    @Query("SELECT e.fullName.name AS name, e.fullName.surname AS surname, COALESCE(e.fullName.patronymic, '') AS patronymic " +
            "FROM Actor a JOIN a.employee e JOIN EmployeeAttribute ea ON e.id = ea.employee.id JOIN ea.attribute attr JOIN ea.value av " +
            "WHERE attr.attribute = 'возраст' AND av.value = ?1")
    List<Map<String, String>> findAllActorsByAge(String age);

    @Query("SELECT av.value " +
            "FROM Actor a JOIN a.employee e JOIN Role r ON a.id = r.actor.id JOIN RoleAttribute ra ON r.id = ra.role.id " +
            "JOIN ra.attribute attr JOIN ra.value av " +
            "WHERE a.id = :actorId AND attr.attribute = 'описание роли'")
    List<String> findRoleDescriptionByActorId(@Param("actorId") Long actorId);

    @Query("SELECT COUNT(r) " +
            "FROM Actor a JOIN a.employee e JOIN Role r ON a.id = r.actor.id " +
            "WHERE a.id = :actorId")
    int countRolesByActorId(@Param("actorId") Long actorId);

    @Query("SELECT new com.db.theaterinformationsystem.model.ActorProjection(fn.surname, fn.name, COALESCE(fn.patronymic, '')) as patronymic " +
            "FROM Actor a JOIN a.employee e JOIN e.fullName fn JOIN EmployeeAttribute ea ON e.id = ea.employee.id " +
            "WHERE EXISTS (SELECT 1 " +
            "FROM RoleAttribute ra " +
            "WHERE ra.role.id = :roleId AND ra.attribute = ea.attribute AND ra.value = ea.value) " +
            "GROUP BY fn.surname, fn.name, fn.patronymic")
    List<ActorProjection> findActorsForRole(@Param("roleId") Long roleId);


}