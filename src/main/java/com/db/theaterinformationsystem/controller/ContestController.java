package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.dto.ActorContestDTO;
import com.db.theaterinformationsystem.dto.ContestDTO;
import com.db.theaterinformationsystem.repository.ContestRepository;
import com.db.theaterinformationsystem.service.ContestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/contest")
@RequiredArgsConstructor
@Tag(name = "Конкурсы")
public class ContestController {

    private final ContestService contestService;
    private final ContestRepository contestRepository;

    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody ContestDTO contestDTO) {
        Long id = contestService.save(contestDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestBody ContestDTO contestDTO) {
        contestService.save(contestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/by/id")
    public ContestDTO find(@RequestParam Long id) {
        return contestService.find(id);
    }

    @GetMapping("/find/all")
    public List<ContestDTO> findAll() {
        return contestService.findAll();
    }

    @DeleteMapping("/delete/by/id")
    public ResponseEntity<HttpStatus> delete(@RequestParam Long id) {
        contestRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add/actor/to/contest")
    public ResponseEntity<HttpStatus> addActorToContest(@RequestBody ActorContestDTO dto) {
        contestService.addActorToContest(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}