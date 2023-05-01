package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.repository.MusicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MusicianController {

    private final MusicianRepository musicianRepository;

    @Autowired
    public MusicianController(MusicianRepository musicianRepository) {
        this.musicianRepository = musicianRepository;
    }

    @GetMapping("/musicians/count")
    public long countMusiciansWithStanding(@RequestParam("standing") String standing) {
        return musicianRepository.countMusiciansWithStanding(standing);
    }

    @GetMapping("/musicians")
    public List<Map<String, String>> findAllMusicians() {
        return musicianRepository.findAllMusicians();
    }

    @GetMapping("/musicians/standing")
    public List<Map<String, String>> findMusiciansWithStanding(@RequestParam("standing") String standing) {
        return musicianRepository.findMusiciansWithStanding(standing);
    }
}