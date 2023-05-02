package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.repository.RepertoireRepository;
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
public class RepertoireController {

    private final RepertoireRepository repertoireRepository;

    @Autowired
    public RepertoireController(RepertoireRepository repertoireRepository) {
        this.repertoireRepository = repertoireRepository;
    }

    @GetMapping("/repertoire/count")
    public long countAllRepertoire() {
        return repertoireRepository.countAllRepertoire();
    }

    @GetMapping("/repertoire/plays")
    public List<String> findAllPlaysInRepertoire() {
        return repertoireRepository.findAllPlaysInRepertoire();
    }

    @GetMapping("/repertoire/past-plays")
    public List<String> findPastPlaysInRepertoire() {
        return repertoireRepository.findPastPlaysInRepertoire();
    }

    @GetMapping("/repertoire/genre")
    public List<String> findAllPlaysInRepertoireByGenre(@RequestParam("genreName") String genreName) {
        return repertoireRepository.findAllPlaysInRepertoireByGenre(genreName);
    }

    @GetMapping("/repertoire/date-range")
    public List<String> findAllPlaysInRepertoireBetweenDates(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                             @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return repertoireRepository.findAllPlaysInRepertoireBetweenDates(startDate, endDate);
    }

    @GetMapping("/repertoire/free-seats")
    public Long countFreeSeatsForAllPlays() {
        return repertoireRepository.countFreeSeatsForAllPlays();
    }

    @GetMapping("/repertoire/free-seats/play")
    public Long countFreeSeatsForSpecificPlay(@RequestParam("playId") Long playId) {
        return repertoireRepository.countFreeSeatsForSpecificPlay(playId);
    }

    @GetMapping("/repertoire/sold-tickets-premieres")
    public Long countSoldTicketsForPremieres() {
        return repertoireRepository.countSoldTicketsForPremieres();
    }

    @GetMapping("/repertoire/total-seats-premieres")
    public Long countTotalSeatsForPremieres() {
        return repertoireRepository.countTotalSeatsForPremieres();
    }

}
