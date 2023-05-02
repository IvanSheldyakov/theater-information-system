package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthorController {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("/authors/performed-play")
    public List<Map<String, String>> findPerformedPlayAuthors() {
        return authorRepository.findPerformedPlayAuthors();
    }

    @GetMapping("/authors/century")
    public List<Map<String, String>> findAuthorsByCentury(@RequestParam("century") String century) {
        return authorRepository.findAuthorsByCentury(century);
    }

    @GetMapping("/authors/country")
    public List<Map<String, String>> findAuthorsByCountry(@RequestParam("country") String country) {
        return authorRepository.findAuthorsByCountry(country);
    }

    @GetMapping("/authors/performed-play-genre")
    public List<Map<String, String>> findAuthorsByPerformedPlayGenre(@RequestParam("genre") String genre) {
        return authorRepository.findAuthorsByPerformedPlayGenre(genre);
    }

    @GetMapping("/authors/play-premiere-period")
    public List<Map<String, String>> findAuthorsByPlayPremierePeriod(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                     @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return authorRepository.findAuthorsByPlayPremierePeriod(startDate, endDate);
    }

}