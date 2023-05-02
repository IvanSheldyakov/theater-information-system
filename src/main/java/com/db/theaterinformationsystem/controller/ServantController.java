package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.dto.ServantDTO;
import com.db.theaterinformationsystem.repository.ServantRepository;
import com.db.theaterinformationsystem.service.ServantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/servant")
@RequiredArgsConstructor
@Tag(name = "Служащие")
public class ServantController {

    private final ServantService servantService;
    private final ServantRepository servantRepository;

    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody ServantDTO servantDTO) {
        Long id = servantService.save(servantDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestBody ServantDTO servantDTO) {
        servantService.save(servantDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/by/id")
    public ServantDTO find(@RequestParam Long id) {
        return servantService.find(id);
    }

    @GetMapping("/find/all")
    public List<ServantDTO> findAll() {
        return servantService.findAll();
    }

    @DeleteMapping("/delete/by/id")
    public ResponseEntity<HttpStatus> delete(@RequestParam Long id) {
        servantRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}