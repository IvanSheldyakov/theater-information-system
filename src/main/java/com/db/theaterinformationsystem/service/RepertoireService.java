package com.db.theaterinformationsystem.service;

import com.db.theaterinformationsystem.dto.RepertoireDTO;
import com.db.theaterinformationsystem.mappers.RepertoireMapper;
import com.db.theaterinformationsystem.model.Repertoire;
import com.db.theaterinformationsystem.repository.RepertoireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RepertoireService {

    private final RepertoireMapper repertoireMapper;
    private final RepertoireRepository repertoireRepository;

    @Transactional
    public Long save(RepertoireDTO dto) {
        Repertoire repertoire = repertoireMapper.map(dto);
        Repertoire newRepertoire = repertoireRepository.save(repertoire);
        return newRepertoire.getId();
    }

    public RepertoireDTO find(Long id) {
        return repertoireMapper.map(repertoireRepository.findById(id).orElse(null));
    }

    public List<RepertoireDTO> findAll() {
        return repertoireRepository.findAll().stream().map(repertoireMapper::map).collect(Collectors.toList());
    }
}