package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/actor")
@RequiredArgsConstructor
public class ActorController {

    private final ActorRepository actorRepository;

    @GetMapping("/by/gender/{gender}")
    public ResponseEntity<?> findAllActorsByGender(@PathVariable String gender) {
        return new ResponseEntity<>(actorRepository.findAllActorsByGender(gender), HttpStatus.OK);
    }

    @GetMapping("/by/age/{age}")
    public ResponseEntity<?> findAllActorsByAge(@PathVariable String age) {
        return new ResponseEntity<>(actorRepository.findAllActorsByAge(age), HttpStatus.OK);
    }
}
