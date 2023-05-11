package com.db.theaterinformationsystem.service;

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

    @Transactional
    public Long save(ProducerDTO dto) {

        Producer producer = producerMapper.map(dto);
        Producer newProducer = producerRepository.save(producer);
        return newProducer.getId();
    }

    public ProducerDTO find(Long id) {
        return producerMapper.map(producerRepository.findById(id).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND));
    }

    public List<ProducerDTO> findAll() {
        return producerRepository.findAll().stream().map(producerMapper::map).collect(Collectors.toList());
    }
}
