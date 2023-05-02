package com.db.theaterinformationsystem.service;

import com.db.theaterinformationsystem.dto.ServantDTO;
import com.db.theaterinformationsystem.mappers.ServantMapper;
import com.db.theaterinformationsystem.model.Servant;
import com.db.theaterinformationsystem.repository.ServantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServantService {

    private final ServantMapper servantMapper;
    private final ServantRepository servantRepository;

    @Transactional
    public Long save(ServantDTO dto) {
        Servant servant = servantMapper.map(dto);
        Servant newServant = servantRepository.save(servant);
        return newServant.getId();
    }

    public ServantDTO find(Long id) {
        return servantMapper.map(servantRepository.findById(id).orElse(null));
    }

    public List<ServantDTO> findAll() {
        return servantRepository.findAll().stream().map(servantMapper::map).collect(Collectors.toList());
    }
}