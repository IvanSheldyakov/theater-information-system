package com.db.theaterinformationsystem.repository;

import com.db.theaterinformationsystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT av.value " +
            "FROM Role r JOIN RoleAttribute ra ON r = ra.role " +
            "JOIN Attribute at ON ra.attribute = at JOIN AttributeValue av ON ra.value = av " +
            "WHERE r.actor.id = :actorId AND at.attribute = 'описание роли'")
    List<String> findAllRolesByActorId(@Param("actorId") Long actorId);

    @Query("SELECT COUNT(r) FROM Role r WHERE r.actor.id = :actorId")
    long countRolesByActorId(@Param("actorId") Long actorId);

    @Query("SELECT av.value " +
            "FROM Role r JOIN RoleAttribute ra ON r = ra.role " +
            "JOIN Attribute at ON ra.attribute = at JOIN AttributeValue av ON ra.value = av " +
            "JOIN PlayRole pr ON r = pr.role JOIN Play p ON pr.play = p JOIN Repertoire rep ON p = rep.play " +
            "WHERE r.actor.id = :actorId AND at.attribute = 'описание роли' AND rep.date BETWEEN :startDate AND :endDate")
    List<String> findAllRolesByActorIdAndPeriod(@Param("actorId") Long actorId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT av.value " +
            "FROM Role r JOIN RoleAttribute ra ON r = ra.role " +
            "JOIN Attribute at ON ra.attribute = at JOIN AttributeValue av ON ra.value = av " +
            "JOIN PlayRole pr ON r = pr.role JOIN Play p ON pr.play = p JOIN Genre g ON p.genre = g " +
            "WHERE at.attribute = 'описание роли' AND g.name = :genreName " +
            "GROUP BY av.value")
    List<String> findAllRolesByGenreName(@Param("genreName") String genreName);

    @Query("SELECT av.value " +
            "FROM Role r JOIN RoleAttribute ra ON r = ra.role " +
            "JOIN Attribute at ON ra.attribute = at JOIN AttributeValue av ON ra.value = av " +
            "JOIN PlayRole pr ON r = pr.role JOIN Play p ON pr.play = p JOIN Producer pro ON p.directorProducer = pro " +
            "WHERE at.attribute = 'описание роли' AND pro.id = :producerId")
    List<String> findAllRolesByDirectorProducerId(@Param("producerId") Long producerId);

    @Query("SELECT av.value " +
            "FROM Role r JOIN RoleAttribute ra ON r = ra.role " +
            "JOIN Attribute at ON ra.attribute = at JOIN AttributeValue av ON ra.value = av " +
            "JOIN PlayRole pr ON r = pr.role JOIN Play p ON pr.play = p JOIN Audience aud ON p.audience = aud " +
            "WHERE at.attribute = 'описание роли' AND aud.name = 'детский'")
    List<String> findAllRolesInChildrenPlays();
}
