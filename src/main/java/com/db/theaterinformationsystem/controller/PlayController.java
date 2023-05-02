package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.dto.PlayDTO;
import com.db.theaterinformationsystem.repository.PlayRepository;
import com.db.theaterinformationsystem.service.PlayService;
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
@RequiredArgsConstructor
@Tag(name = "Пьесы")
public class PlayController {

    private final PlayRepository playRepository;
    private final PlayService playService;

    @GetMapping("/plays")
    @Operation(description = "список пьес")
    public List<String> findAllPlays() {
        return playRepository.findAllPlays();
    }

    @GetMapping("/plays/genre")
    @Operation(description = "список пьес по жанру")
    public List<String> findPlaysByGenre(@RequestParam("genreName") String genreName) {
        return playRepository.findPlaysByGenreName(genreName);
    }

    @Operation(description = "список пьес по автору")
    @GetMapping("/plays/author")
    public List<String> findPlaysByAuthorFullName(@RequestParam("authorName") String authorName,
                                                  @RequestParam("authorSurname") String authorSurname,
                                                  @RequestParam("authorPatronymic") String authorPatronymic) {
        return playRepository.findPlaysByAuthorFullName(authorName, authorSurname, authorPatronymic);
    }

    @Operation(description = "список пьес по стране автора")
    @GetMapping("/plays/author/country")
    public List<String> findPlaysByAuthorCountry(@RequestParam("country") String country) {
        return playRepository.findPlaysByAuthorCountry(country);
    }

    @Operation(description = "список пьес по веку создания")
    @GetMapping("/plays/century")
    public List<String> findPlaysByCreateCentury(@RequestParam("century") Integer century) {
        return playRepository.findPlaysByCreateCentury(century);
    }

    @Operation(description = "список пьес с премьерой в промежутке")
    @GetMapping("/plays/premiere")
    public List<String> findPlaysByPremierePeriod(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                  @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return playRepository.findPlaysByPremierePeriod(startDate, endDate);
    }

    @Operation(description = "список пьес прошедших до сегодня")
    @GetMapping("/plays/before-current-date")
    public List<String> findAllBeforeCurrentDate() {
        return playRepository.findAllBeforeCurrentDate();
    }

    @Operation(description = "список пьес в промежутке")
    @GetMapping("/plays/between-dates")
    public List<String> findAllBetweenDates(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return playRepository.findAllBetweenDates(startDate, endDate);
    }

    @Operation(description = "список актеров пьесы")
    @GetMapping("/plays/actors")
    public List<Map<String, String>> findActorsByPlayId(@RequestParam("playId") Long playId) {
        return playRepository.findActorsByPlayId(playId);
    }

    @Operation(description = "список дублюров пьесы")
    @GetMapping("/plays/backups")
    public List<Map<String, String>> findBackupsByPlayId(@RequestParam("playId") Long playId) {
        return playRepository.findBackupsByPlayId(playId);
    }

    @Operation(description = "список постановщиков пьесы")
    @GetMapping("/plays/producers")
    public List<Map<String, String>> findProducersByPlayId(@RequestParam("playId") Long playId) {
        return playRepository.findProducersByPlayId(playId);
    }

    @Operation(description = "список авторов пьес у которых прошла премьера")
    @GetMapping("/plays/authors/past-premieres")
    public List<Map<String, String>> findAuthorsWithPastPremieres() {
        return playRepository.findAuthorsWithPastPremieres();
    }

    @Operation(description = "премьера пьесы")
    @GetMapping("/plays/premiere-date")
    public LocalDate findPremiereDateByPlayId(@RequestParam("playId") Long playId) {
        return playRepository.findPremiereDateByPlayId(playId);
    }

    @PostMapping("/plays/create")
    public ResponseEntity<Long> create(@RequestBody PlayDTO playDTO) {
        Long id = playService.save(playDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PostMapping("/plays/update")
    public ResponseEntity<HttpStatus> update(@RequestBody PlayDTO playDTO) {
        playService.save(playDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/plays/find/by/id")
    public PlayDTO find(@RequestParam Long id) {
        return playService.find(id);
    }

    @GetMapping("/plays/find/all")
    public List<PlayDTO> findAll() {
        return playService.findAll();
    }

    @DeleteMapping("/plays/delete/by/id")
    public ResponseEntity<HttpStatus> delete(@RequestParam Long id) {
        playRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}