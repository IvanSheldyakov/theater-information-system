package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.model.ActorProjection;
import com.db.theaterinformationsystem.repository.ActorRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/actor")
@RequiredArgsConstructor
@Tag(name = "актеры")
public class ActorController {

    private final ActorRepository actorRepository;

    @Operation(description = "колличество")
    @GetMapping("/count")
    public long countActors() {
        return actorRepository.count();
    }

    @Operation(description = "число актеров с зарплатой")
    @GetMapping("/actors/count/standing")
    public long countActorsWithStanding(@RequestParam("standing") String standing) {
        return actorRepository.countActorsWithStanding(standing);
    }

    @Operation(description = "список актеров")
    @GetMapping
    public List<Map<String, String>> findAllActors() {
        return actorRepository.findAllActors();
    }

    @Operation(description = "список актеров с зарплатой")
    @GetMapping("/with-standing")
    public List<Map<String, String>> findActorsWithStanding(@RequestParam("standing") String standing) {
        return actorRepository.findActorsWithStanding(standing);
    }

    @Operation(description = "список актеров со званиями")
    @GetMapping("/with-titles")
    public List<Map<String, String>> findAllActorsWithTitles() {
        return actorRepository.findAllActorsWithTitles();
    }

    @Operation(description = "список актеров с полученными званиями за период")
    @GetMapping("/with-titles/period")
    public List<Map<String, String>> findAllActorsWithTitlesInPeriod(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                     @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return actorRepository.findAllActorsWithTitlesInPeriod(startDate, endDate);
    }

    @Operation(description = "список актеров получивших звания в контесте")
    @GetMapping("/with-titles/contest")
    public List<Map<String, String>> findAllActorsWithTitlesInContest(@RequestParam("contestName") String contestName) {
        return actorRepository.findAllActorsWithTitlesInContest(contestName);
    }

    @Operation(description = "список актеров по полу")
    @GetMapping("/by-gender")
    public List<Map<String, String>> findAllActorsByGender(@RequestParam("gender") String gender) {
        return actorRepository.findAllActorsByGender(gender);
    }

    @Operation(description = "список актеров по возрасту")
    @GetMapping("/by-age")
    public List<Map<String, String>> findAllActorsByAge(@RequestParam("age") String age) {
        return actorRepository.findAllActorsByAge(age);
    }

    @Operation(description = "описание ролей по id актера")
    @GetMapping("/{actorId}/role-descriptions")
    public List<String> findRoleDescriptionByActorId(@PathVariable("actorId") Long actorId) {
        return actorRepository.findRoleDescriptionByActorId(actorId);
    }

    @Operation(description = "кол-во ролей актера")
    @GetMapping("/{actorId}/role-count")
    public int countRolesByActorId(@PathVariable("actorId") Long actorId) {
        return actorRepository.countRolesByActorId(actorId);
    }

    @Operation(description = "список актеров подходящих под роль")
    @GetMapping("/for-role/{roleId}")
    public List<ActorProjection> findActorsForRole(@PathVariable("roleId") Long roleId) {
        return actorRepository.findActorsForRole(roleId);
    }
}
