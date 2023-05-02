package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.repository.PlayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlayController {

    private final PlayRepository playRepository;

    @GetMapping("/plays")
    public List<String> findAllPlays() {
        return playRepository.findAllPlays();
    }

    @GetMapping("/plays/genre")
    public List<String> findPlaysByGenreName(@RequestParam("genreName") String genreName) {
        return playRepository.findPlaysByGenreName(genreName);
    }

    @GetMapping("/plays/author")
    public List<String> findPlaysByAuthorFullName(@RequestParam("authorName") String authorName,
                                                  @RequestParam("authorSurname") String authorSurname,
                                                  @RequestParam("authorPatronymic") String authorPatronymic) {
        return playRepository.findPlaysByAuthorFullName(authorName, authorSurname, authorPatronymic);
    }

    @GetMapping("/plays/author/country")
    public List<String> findPlaysByAuthorCountry(@RequestParam("country") String country) {
        return playRepository.findPlaysByAuthorCountry(country);
    }

    @GetMapping("/plays/century")
    public List<String> findPlaysByCreateCentury(@RequestParam("century") Integer century) {
        return playRepository.findPlaysByCreateCentury(century);
    }

    @GetMapping("/plays/premiere")
    public List<String> findPlaysByPremierePeriod(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                  @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return playRepository.findPlaysByPremierePeriod(startDate, endDate);
    }

    @GetMapping("/plays/before-current-date")
    public List<String> findAllBeforeCurrentDate() {
        return playRepository.findAllBeforeCurrentDate();
    }

    @GetMapping("/plays/between-dates")
    public List<String> findAllBetweenDates(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return playRepository.findAllBetweenDates(startDate, endDate);
    }

    @GetMapping("/plays/actors")
    public List<Object[]> findActorsByPlayId(@RequestParam("playId") Long playId) {
        return playRepository.findActorsByPlayId(playId);
    }

    @GetMapping("/plays/backups")
    public List<Object[]> findBackupsByPlayId(@RequestParam("playId") Long playId) {
        return playRepository.findBackupsByPlayId(playId);
    }

    @GetMapping("/plays/producers")
    public List<Object[]> findProducersByPlayId(@RequestParam("playId") Long playId) {
        return playRepository.findProducersByPlayId(playId);
    }

    @GetMapping("/plays/authors/past-premieres")
    public List<Object[]> findAuthorsWithPastPremieres() {
        return playRepository.findAuthorsWithPastPremieres();
    }

    @GetMapping("/plays/premiere-date")
    public LocalDate findPremiereDateByPlayId(@RequestParam("playId") Long playId) {
        return playRepository.findPremiereDateByPlayId(playId);
    }
}