package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.repository.TourRepository;
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
public class TourController {

    private final TourRepository tourRepository;

    @Autowired
    public TourController(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @GetMapping("/tours/actors-producers/period")
    public List<Map<String, String>> findActorsAndProducersInTourByPeriod(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                          @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return tourRepository.findActorsAndProducersInTourByPeriod(startDate, endDate);
    }

    @GetMapping("/tours/actors-producers/play-time")
    public List<Map<String, String>> findActorsAndProducersInTourByPlayAndTime(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                               @RequestParam("playId") Long playId) {
        return tourRepository.findActorsAndProducersInTourByPlayAndTime(startDate, playId);
    }
}
