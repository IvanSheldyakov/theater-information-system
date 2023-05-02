package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.dto.MusicianDTO;
import com.db.theaterinformationsystem.repository.MusicianRepository;
import com.db.theaterinformationsystem.service.MusicianService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Музыканты")
public class MusicianController {

    private final MusicianRepository musicianRepository;
    private final MusicianService musicianService;

    @GetMapping("/musicians/count")
    @Operation(description = "кол-во музыкантов со стажем")
    public long countMusiciansWithStanding(@RequestParam("standing") String standing) {
        return musicianRepository.countMusiciansWithStanding(standing);
    }

    @Operation(description = "список музыкантов")
    @GetMapping("/musicians")
    public List<Map<String, String>> findAllMusicians() {
        return musicianRepository.findAllMusicians();
    }

    @Operation(description = "список музыкантов со стажем")
    @GetMapping("/musicians/standing")
    public List<Map<String, String>> findMusiciansWithStanding(@RequestParam("standing") String standing) {
        return musicianRepository.findMusiciansWithStanding(standing);
    }

    @PostMapping("/musicians/create")
    public ResponseEntity<Long> create(@RequestBody MusicianDTO musicianDTO) {
        Long id = musicianService.save(musicianDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PostMapping("/musicians/update")
    public ResponseEntity<HttpStatus> update(@RequestBody MusicianDTO musicianDTO) {
        musicianService.save(musicianDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/musicians/find/by/id")
    public MusicianDTO find(@RequestParam Long id) {
        return musicianService.find(id);
    }

    @GetMapping("/musicians/find/all")
    public List<MusicianDTO> findAll() {
        return musicianService.findAll();
    }

    @DeleteMapping("/musicians/delete/by/id")
    public ResponseEntity<HttpStatus> delete(@RequestParam Long id) {
        musicianRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}