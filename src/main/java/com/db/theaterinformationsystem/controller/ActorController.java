package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.model.ActorProjection;
import com.db.theaterinformationsystem.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/actor")
@RequiredArgsConstructor
public class ActorController {

    private final ActorRepository actorRepository;

    @GetMapping("/count")
    public long countActors() {
        return actorRepository.count();
    }

    @GetMapping("/actors/count/standing")
    public long countActorsWithStanding(@RequestParam("standing") String standing) {
        return actorRepository.countActorsWithStanding(standing);
    }

    @GetMapping
    public List<Map<String, String>> findAllActors() {
        return actorRepository.findAllActors();
    }

    @GetMapping("/with-titles")
    public List<Map<String, String>> findAllActorsWithTitles() {
        return actorRepository.findAllActorsWithTitles();
    }

    @GetMapping("/with-titles/period")
    public List<Map<String, String>> findAllActorsWithTitlesInPeriod(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                                     @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return actorRepository.findAllActorsWithTitlesInPeriod(startDate, endDate);
    }

    @GetMapping("/with-titles/contest")
    public List<Map<String, String>> findAllActorsWithTitlesInContest(@RequestParam("contestName") String contestName) {
        return actorRepository.findAllActorsWithTitlesInContest(contestName);
    }

    @GetMapping("/by-gender")
    public List<Map<String, String>> findAllActorsByGender(@RequestParam("gender") String gender) {
        return actorRepository.findAllActorsByGender(gender);
    }

    @GetMapping("/by-age")
    public List<Map<String, String>> findAllActorsByAge(@RequestParam("age") String age) {
        return actorRepository.findAllActorsByAge(age);
    }

    @GetMapping("/{actorId}/role-descriptions")
    public List<String> findRoleDescriptionByActorId(@PathVariable("actorId") Long actorId) {
        return actorRepository.findRoleDescriptionByActorId(actorId);
    }

    @GetMapping("/{actorId}/role-count")
    public int countRolesByActorId(@PathVariable("actorId") Long actorId) {
        return actorRepository.countRolesByActorId(actorId);
    }

    @GetMapping("/for-role/{roleId}")
    public List<ActorProjection> findActorsForRole(@PathVariable("roleId") Long roleId) {
        return actorRepository.findActorsForRole(roleId);
    }
}
