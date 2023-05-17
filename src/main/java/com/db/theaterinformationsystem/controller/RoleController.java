package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.dto.RoleAttributeDTO;
import com.db.theaterinformationsystem.dto.RoleCreateDTO;
import com.db.theaterinformationsystem.dto.RoleDTO;
import com.db.theaterinformationsystem.repository.RoleRepository;
import com.db.theaterinformationsystem.service.AttributeService;
import com.db.theaterinformationsystem.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Роли")
@RequiredArgsConstructor
public class RoleController {

    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final AttributeService attributeService;


    @GetMapping("/roles/actor")
    @Operation(description = "роли актера")
    public List<String> findAllRolesByActorId(@RequestParam("actorId") Long actorId) {
        return roleRepository.findAllRolesByActorId(actorId);
    }

    @Operation(description = "кол-во ролей актера")
    @GetMapping("/roles/actor/count")
    public long countRolesByActorId(@RequestParam("actorId") Long actorId) {
        return roleRepository.countRolesByActorId(actorId);
    }

    @Operation(description = "роли актера за промежуток")
    @GetMapping("/roles/actor/period")
    public List<String> findAllRolesByActorIdAndPeriod(@RequestParam("actorId") Long actorId,
                                                       @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                       @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return roleRepository.findAllRolesByActorIdAndPeriod(actorId, startDate, endDate);
    }

    @Operation(description = "роли по жанру")
    @GetMapping("/roles/genre")
    public List<String> findAllRolesByGenreName(@RequestParam("genreName") String genreName) {
        return roleRepository.findAllRolesByGenreName(genreName);
    }

    @Operation(description = "роли в пьесах постановщика")
    @GetMapping("/roles/producer")
    public List<String> findAllRolesByDirectorProducerId(@RequestParam("producerId") Long producerId) {
        return roleRepository.findAllRolesByDirectorProducerId(producerId);
    }

    @Operation(description = "роли в детских пьесах")
    @GetMapping("/roles/children-plays")
    public List<String> findAllRolesInChildrenPlays() {
        return roleRepository.findAllRolesInChildrenPlays();
    }

    @PostMapping("/roles/create")
    public ResponseEntity<Long> create(@RequestBody RoleCreateDTO roleDTO) {
        Long id = roleService.save(roleDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PostMapping("/roles/update")
    public ResponseEntity<HttpStatus> update(@RequestBody RoleCreateDTO roleDTO) {
        roleService.update(roleDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/roles/find/by/id")
    public RoleCreateDTO find(@RequestParam Long id) {
        return roleService.find(id);
    }

    @GetMapping("/roles/find/all")
    public List<RoleDTO> findAll() {
        return roleService.findAll();
    }

    @DeleteMapping("/roles/delete/by/id")
    public ResponseEntity<HttpStatus> delete(@RequestParam Long id) {
        roleRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/roles/add/attribute")
    public ResponseEntity<HttpStatus> addAttribute(@RequestBody RoleAttributeDTO dto) {
        attributeService.addAttribute(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}