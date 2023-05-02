package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.dto.AudienceDTO;
import com.db.theaterinformationsystem.repository.AudienceRepository;
import com.db.theaterinformationsystem.service.AudienceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/audience")
@RequiredArgsConstructor
@Tag(name = "Аудитории")
public class AudienceController {

    private final AudienceService audienceService;
    private final AudienceRepository audienceRepository;

    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody AudienceDTO audienceDTO) {
        Long id = audienceService.save(audienceDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestBody AudienceDTO audienceDTO) {
        audienceService.save(audienceDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/by/id")
    public AudienceDTO find(@RequestParam Long id) {
        return audienceService.find(id);
    }

    @GetMapping("/find/all")
    public List<AudienceDTO> findAll() {
        return audienceService.findAll();
    }

    @DeleteMapping("/delete/by/id")
    public ResponseEntity<HttpStatus> delete(@RequestParam Long id) {
        audienceRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
