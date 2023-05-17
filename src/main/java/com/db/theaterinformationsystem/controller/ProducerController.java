package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.dto.ProducerCreateDTO;
import com.db.theaterinformationsystem.dto.ProducerDTO;
import com.db.theaterinformationsystem.repository.ProducerRepository;
import com.db.theaterinformationsystem.service.ProducerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/producer")
@RequiredArgsConstructor
@Tag(name = "Постановщики")
public class ProducerController {

    private final ProducerService producerService;
    private final ProducerRepository producerRepository;

    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody ProducerCreateDTO ProducerDTO) {
        Long id = producerService.save(ProducerDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestBody ProducerCreateDTO ProducerDTO) {
        producerService.update(ProducerDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/by/id")
    public ProducerCreateDTO find(@RequestParam Long id) {
        return producerService.find(id);
    }

    @GetMapping("/find/all")
    public List<ProducerDTO> findAll() {
        return producerService.findAll();
    }

    @DeleteMapping("/delete/by/id")
    public ResponseEntity<HttpStatus> delete(@RequestParam Long id) {
        producerRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
