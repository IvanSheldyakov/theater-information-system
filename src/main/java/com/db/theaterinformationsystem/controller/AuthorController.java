package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.dto.AuthorDTO;
import com.db.theaterinformationsystem.repository.AuthorRepository;
import com.db.theaterinformationsystem.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Авторы")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorRepository authorRepository;

    @Operation(description = "список авторов сыгранных спектаклей")
    @GetMapping("/authors/performed-play")
    public List<Map<String, String>> findPerformedPlayAuthors() {
        return authorRepository.findPerformedPlayAuthors();
    }

    @Operation(description = "список авторов по веку")
    @GetMapping("/authors/century")
    public List<Map<String, String>> findAuthorsByCentury(@RequestParam("century") String century) {
        return authorRepository.findAuthorsByCentury(century);
    }

    @Operation(description = "список авторов по стране")
    @GetMapping("/authors/country")
    public List<Map<String, String>> findAuthorsByCountry(@RequestParam("country") String country) {
        return authorRepository.findAuthorsByCountry(country);
    }

    @Operation(description = "список авторов когда либо поставленных спектаклей указанного жанра")
    @GetMapping("/authors/performed-play-genre")
    public List<Map<String, String>> findAuthorsByPerformedPlayGenre(@RequestParam("genre") String genre) {
        return authorRepository.findAuthorsByPerformedPlayGenre(genre);
    }

    @Operation(description = "список авторов поставленных спектаклей за указанный период")
    @GetMapping("/authors/play-premiere-period")
    public List<Map<String, String>> findAuthorsByPlayPremierePeriod(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                     @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return authorRepository.findAuthorsByPlayPremierePeriod(startDate, endDate);
    }

    @PostMapping("/authors/create")
    public ResponseEntity<Long> create(@RequestBody AuthorDTO authorDTO) {
        Long id = authorService.save(authorDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PostMapping("/authors/update")
    public ResponseEntity<HttpStatus> update(@RequestBody AuthorDTO authorDTO) {
        authorService.save(authorDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/authors/find/by/id")
    public AuthorDTO find(@RequestParam Long id) {
        return authorService.find(id);
    }

    @GetMapping("/authors/find/all")
    public List<AuthorDTO> findAll() {
        return authorService.findAll();
    }

    @DeleteMapping("/authors/delete/by/id")
    public ResponseEntity<HttpStatus> delete(@RequestParam Long id) {
        authorRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}