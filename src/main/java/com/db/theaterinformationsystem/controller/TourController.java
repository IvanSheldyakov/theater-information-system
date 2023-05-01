package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class TourController {

    private final TourRepository tourRepository;

    @Autowired
    public TourController(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @GetMapping("/tours/actors-producers/period")
    public List<Map<String, String>> findActorsAndProducersInTourByPeriod(@RequestParam("startDate") LocalDate startDate,
                                                                          @RequestParam("endDate") LocalDate endDate) {
        return tourRepository.findActorsAndProducersInTourByPeriod(startDate, endDate);
    }

    @GetMapping("/tours/actors-producers/play-time")
    public List<Map<String, String>> findActorsAndProducersInTourByPlayAndTime(@RequestParam("startDate") LocalDate startDate,
                                                                               @RequestParam("playId") Long playId) {
        return tourRepository.findActorsAndProducersInTourByPlayAndTime(startDate, playId);
    }
}
