package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.dto.TourDTO;
import com.db.theaterinformationsystem.dto.TourPlayDTO;
import com.db.theaterinformationsystem.repository.TourRepository;
import com.db.theaterinformationsystem.service.TourService;
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
@Tag(name = "Туры")
public class TourController {

    private final TourRepository tourRepository;
    private final TourService tourService;


    @GetMapping("/tours/actors-producers/period")
    @Operation(description = "Список актеров и постановщика в туре за указанный период")
    public List<Map<String, String>> findActorsAndProducersInTourByPeriod(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                          @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return tourRepository.findActorsAndProducersInTourByPeriod(startDate, endDate);
    }

    @GetMapping("/tours/actors-producers/play-time")
    @Operation(description = "Найти актеров и постановщиков в туре по пьесе и времени старта тура")
    public List<Map<String, String>> findActorsAndProducersInTourByPlayAndTime(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                               @RequestParam("playId") Long playId) {
        return tourRepository.findActorsAndProducersInTourByPlayAndTime(startDate, playId);
    }

    @PostMapping("/tours/create")
    public ResponseEntity<Long> create(@RequestBody TourDTO tourDTO) {
        Long id = tourService.save(tourDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PostMapping("/tours/update")
    public ResponseEntity<HttpStatus> update(@RequestBody TourDTO tourDTO) {
        tourService.save(tourDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/tours/find/by/id")
    public TourDTO find(@RequestParam Long id) {
        return tourService.find(id);
    }

    @GetMapping("/tours/find/all")
    public List<TourDTO> findAll() {
        return tourService.findAll();
    }

    @DeleteMapping("/tours/delete/by/id")
    public ResponseEntity<HttpStatus> delete(@RequestParam Long id) {
        tourRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/tours/add/play")
    public ResponseEntity<HttpStatus> addPlay(@RequestBody TourPlayDTO dto) {
        tourService.addPlay(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
