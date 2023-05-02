package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.dto.GenreDTO;
import com.db.theaterinformationsystem.repository.GenreRepository;
import com.db.theaterinformationsystem.service.GenreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/genre")
@RequiredArgsConstructor
@Tag(name = "Жанры")
public class GenreController {

    private final GenreService genreService;
    private final GenreRepository genreRepository;

    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody GenreDTO genreDTO) {
        Long id = genreService.save(genreDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestBody GenreDTO genreDTO) {
        genreService.save(genreDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/by/id")
    public GenreDTO find(@RequestParam Long id) {
        return genreService.find(id);
    }

    @GetMapping("/find/all")
    public List<GenreDTO> findAll() {
        return genreService.findAll();
    }

    @DeleteMapping("/delete/by/id")
    public ResponseEntity<HttpStatus> delete(@RequestParam Long id) {
        genreRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}