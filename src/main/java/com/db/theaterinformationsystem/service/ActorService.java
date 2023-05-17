package com.db.theaterinformationsystem.service;

import com.db.theaterinformationsystem.dto.ActorCreateDTO;
import com.db.theaterinformationsystem.dto.ActorDTO;
import com.db.theaterinformationsystem.dto.EmployeeAttributeDTO;
import com.db.theaterinformationsystem.exception.ExceptionSupplier;
import com.db.theaterinformationsystem.mappers.ActorMapper;
import com.db.theaterinformationsystem.model.Actor;
import com.db.theaterinformationsystem.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorMapper actorMapper;
    private final ActorRepository actorRepository;

    private final AttributeService attributeService;

    @Transactional
    public Long save(ActorCreateDTO dto) {

        Actor actor = actorMapper.map(dto);
        Actor newActor = actorRepository.save(actor);

        EmployeeAttributeDTO sexAttribute = new EmployeeAttributeDTO();
        sexAttribute.setEmployeeId(newActor.getEmployee().getId());
        sexAttribute.setName("пол");
        sexAttribute.setValue(dto.getSex());
        attributeService.addAttribute(sexAttribute);

        EmployeeAttributeDTO ageAttribute = new EmployeeAttributeDTO();
        ageAttribute.setEmployeeId(newActor.getEmployee().getId());
        ageAttribute.setName("возраст");
        ageAttribute.setValue(dto.getAge());
        attributeService.addAttribute(ageAttribute);
        return newActor.getId();
    }

    @Transactional
    public Long update(ActorCreateDTO dto) {
        Actor actor = actorMapper.map(dto);
        Actor newActor = actorRepository.save(actor);

        EmployeeAttributeDTO sexAttribute = new EmployeeAttributeDTO();
        sexAttribute.setEmployeeId(newActor.getEmployee().getId());
        sexAttribute.setName("пол");
        sexAttribute.setValue(dto.getSex());
        attributeService.updateAttribute(sexAttribute);

        EmployeeAttributeDTO ageAttribute = new EmployeeAttributeDTO();
        ageAttribute.setEmployeeId(newActor.getEmployee().getId());
        ageAttribute.setName("возраст");
        ageAttribute.setValue(dto.getAge());
        attributeService.updateAttribute(ageAttribute);
        return newActor.getId();
    }

    public ActorCreateDTO find(Long id) {
        Actor actor = actorRepository.findById(id).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        ActorCreateDTO dto =  actorMapper.mapActor(actor);
        dto.setAge(attributeService.findAttributeValue(actor.getEmployee(),"возраст"));
        dto.setSex(attributeService.findAttributeValue(actor.getEmployee(),"пол"));
        return dto;
    }

    public List<ActorDTO> findAll() {
        return actorRepository.findAll().stream().map(actorMapper::map).collect(Collectors.toList());
    }
}
