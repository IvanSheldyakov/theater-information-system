package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/roles/actor")
    public List<String> findAllRolesByActorId(@RequestParam("actorId") Long actorId) {
        return roleRepository.findAllRolesByActorId(actorId);
    }

    @GetMapping("/roles/actor/count")
    public long countRolesByActorId(@RequestParam("actorId") Long actorId) {
        return roleRepository.countRolesByActorId(actorId);
    }

    @GetMapping("/roles/actor/period")
    public List<String> findAllRolesByActorIdAndPeriod(@RequestParam("actorId") Long actorId,
                                                       @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                       @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return roleRepository.findAllRolesByActorIdAndPeriod(actorId, startDate, endDate);
    }

    @GetMapping("/roles/genre")
    public List<String> findAllRolesByGenreName(@RequestParam("genreName") String genreName) {
        return roleRepository.findAllRolesByGenreName(genreName);
    }

    @GetMapping("/roles/producer")
    public List<String> findAllRolesByDirectorProducerId(@RequestParam("producerId") Long producerId) {
        return roleRepository.findAllRolesByDirectorProducerId(producerId);
    }

    @GetMapping("/roles/children-plays")
    public List<String> findAllRolesInChildrenPlays() {
        return roleRepository.findAllRolesInChildrenPlays();
    }
}