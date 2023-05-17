package com.db.theaterinformationsystem.service;

import com.db.theaterinformationsystem.dto.ServantCreateDTO;
import com.db.theaterinformationsystem.dto.ServantDTO;
import com.db.theaterinformationsystem.exception.ExceptionSupplier;
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
    private final AttributeService attributeService;

    @Transactional
    public Long save(ServantCreateDTO dto) {
        Servant servant = servantMapper.map(dto);
        Servant newServant = servantRepository.save(servant);
        attributeService.setSexAttribute(newServant.getEmployee(), dto.getSex());
        attributeService.setAgeAttribute(newServant.getEmployee(), dto.getAge());
        return newServant.getId();
    }

    @Transactional
    public Long update(ServantCreateDTO dto) {
        Servant servant = servantMapper.map(dto);
        Servant newServant = servantRepository.save(servant);
        attributeService.updateSexAttribute(newServant.getEmployee(), dto.getSex());
        attributeService.updateAgeAttribute(newServant.getEmployee(), dto.getAge());
        return newServant.getId();
    }

    public ServantCreateDTO find(Long id) {
        Servant servant = servantRepository.findById(id).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        ServantCreateDTO dto = servantMapper.mapServant(servant);
        dto.setAge(attributeService.findAttributeValue(servant.getEmployee(), "возраст"));
        dto.setSex(attributeService.findAttributeValue(servant.getEmployee(), "пол"));
        return dto;
    }

    public List<ServantDTO> findAll() {
        return servantRepository.findAll().stream().map(servantMapper::map).collect(Collectors.toList());
    }
}