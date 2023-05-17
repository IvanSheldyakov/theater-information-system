package com.db.theaterinformationsystem.service;

import com.db.theaterinformationsystem.dto.MusicianCreateDTO;
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
    private final AttributeService attributeService;

    @Transactional
    public Long save(MusicianCreateDTO dto) {

        Musician musician = musicianMapper.map(dto);
        Musician newMusician = musicianRepository.save(musician);

        attributeService.setAgeAttribute(newMusician.getEmployee(), dto.getAge());
        attributeService.setSexAttribute(newMusician.getEmployee(), dto.getSex());

        return newMusician.getId();
    }

    @Transactional
    public Long update(MusicianCreateDTO dto) {
        Musician musician = musicianMapper.map(dto);
        Musician newMusician = musicianRepository.save(musician);

        attributeService.updateAgeAttribute(newMusician.getEmployee(), dto.getAge());
        attributeService.updateSexAttribute(newMusician.getEmployee(), dto.getSex());
        return newMusician.getId();
    }

    public MusicianCreateDTO find(Long id) {
        Musician musician = musicianRepository.findById(id).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        MusicianCreateDTO dto = musicianMapper.mapMusician(musician);
        dto.setAge(attributeService.findAttributeValue(musician.getEmployee(), "возраст"));
        dto.setSex(attributeService.findAttributeValue(musician.getEmployee(), "пол"));
        return dto;
    }

    public List<MusicianDTO> findAll() {
        return musicianRepository.findAll().stream().map(musicianMapper::map).collect(Collectors.toList());
    }
}