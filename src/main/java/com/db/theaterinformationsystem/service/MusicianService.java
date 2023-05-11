package com.db.theaterinformationsystem.service;

import com.db.theaterinformationsystem.dto.MusicianDTO;
import com.db.theaterinformationsystem.exception.ExceptionSupplier;
import com.db.theaterinformationsystem.mappers.MusicianMapper;
import com.db.theaterinformationsystem.model.Musician;
import com.db.theaterinformationsystem.repository.MusicianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MusicianService {

    private final MusicianMapper musicianMapper;
    private final MusicianRepository musicianRepository;

    @Transactional
    public Long save(MusicianDTO dto) {

        Musician musician = musicianMapper.map(dto);
        Musician newMusician = musicianRepository.save(musician);
        return newMusician.getId();
    }

    public MusicianDTO find(Long id) {
        return musicianMapper.map(musicianRepository.findById(id).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND));
    }

    public List<MusicianDTO> findAll() {
        return musicianRepository.findAll().stream().map(musicianMapper::map).collect(Collectors.toList());
    }
}