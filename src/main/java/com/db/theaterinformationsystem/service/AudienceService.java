package com.db.theaterinformationsystem.service;

import com.db.theaterinformationsystem.dto.AudienceDTO;
import com.db.theaterinformationsystem.mappers.PlayMapper;
import com.db.theaterinformationsystem.model.Audience;
import com.db.theaterinformationsystem.repository.AudienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AudienceService {

    private final PlayMapper audienceMapper;
    private final AudienceRepository audienceRepository;

    @Transactional
    public Long save(AudienceDTO dto) {
        Audience audience = audienceMapper.map(dto);
        Audience newAudience = audienceRepository.save(audience);
        return newAudience.getId();
    }

    public AudienceDTO find(Long id) {
        return audienceMapper.map(audienceRepository.findById(id).orElse(null));
    }

    public List<AudienceDTO> findAll() {
        return audienceRepository.findAll().stream().map(audienceMapper::map).collect(Collectors.toList());
    }
}