package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.dto.RepertoireDTO;
import com.db.theaterinformationsystem.repository.RepertoireRepository;
import com.db.theaterinformationsystem.service.RepertoireService;
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
@RequiredArgsConstructor
@Tag(name = "Репертуар")
public class RepertoireController {

    private final RepertoireRepository repertoireRepository;
    private final RepertoireService repertoireService;

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

    @PostMapping("/repertoire/create")
    public ResponseEntity<Long> create(@RequestBody RepertoireDTO repertoireDTO) {
        Long id = repertoireService.save(repertoireDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PostMapping("/repertoire/update")
    public ResponseEntity<HttpStatus> update(@RequestBody RepertoireDTO repertoireDTO) {
        repertoireService.save(repertoireDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/repertoire/find/by/id")
    public RepertoireDTO find(@RequestParam Long id) {
        return repertoireService.find(id);
    }

    @GetMapping("/repertoire/find/all")
    public List<RepertoireDTO> findAll() {
        return repertoireService.findAll();
    }

    @DeleteMapping("/repertoire/delete/by/id")
    public ResponseEntity<HttpStatus> delete(@RequestParam Long id) {
        repertoireRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
