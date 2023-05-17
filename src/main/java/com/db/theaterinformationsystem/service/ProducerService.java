package com.db.theaterinformationsystem.service;

import com.db.theaterinformationsystem.dto.ProducerCreateDTO;
import com.db.theaterinformationsystem.dto.ProducerDTO;
import com.db.theaterinformationsystem.exception.ExceptionSupplier;
import com.db.theaterinformationsystem.mappers.ProducerMapper;
import com.db.theaterinformationsystem.model.Producer;
import com.db.theaterinformationsystem.repository.ProducerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final ProducerMapper producerMapper;
    private final ProducerRepository producerRepository;
    private final AttributeService attributeService;

    @Transactional
    public Long save(ProducerCreateDTO dto) {

        Producer producer = producerMapper.map(dto);
        Producer newProducer = producerRepository.save(producer);
        attributeService.setSexAttribute(newProducer.getEmployee(), dto.getSex());
        attributeService.setAgeAttribute(newProducer.getEmployee(), dto.getAge());
        return newProducer.getId();
    }

    @Transactional
    public Long update(ProducerCreateDTO dto) {

        Producer producer = producerMapper.map(dto);
        Producer newProducer = producerRepository.save(producer);
        attributeService.updateSexAttribute(newProducer.getEmployee(), dto.getSex());
        attributeService.updateAgeAttribute(newProducer.getEmployee(), dto.getAge());
        return newProducer.getId();
    }

    public ProducerCreateDTO find(Long id) {
        Producer producer = producerRepository.findById(id).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        ProducerCreateDTO dto = producerMapper.mapProducer(producer);
        dto.setAge(attributeService.findAttributeValue(producer.getEmployee(), "возраст"));
        dto.setSex(attributeService.findAttributeValue(producer.getEmployee(), "пол"));
        return dto;
    }

    public List<ProducerDTO> findAll() {
        return producerRepository.findAll().stream().map(producerMapper::map).collect(Collectors.toList());
    }
}
