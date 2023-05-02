package com.db.theaterinformationsystem.service;

import com.db.theaterinformationsystem.dto.ContestDTO;
import com.db.theaterinformationsystem.mappers.ContestMapper;
import com.db.theaterinformationsystem.model.Contest;
import com.db.theaterinformationsystem.repository.ContestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContestService {

    private final ContestMapper contestMapper;
    private final ContestRepository contestRepository;

    @Transactional
    public Long save(ContestDTO dto) {
        Contest contest = contestMapper.map(dto);
        Contest newContest = contestRepository.save(contest);
        return newContest.getId();
    }

    public ContestDTO find(Long id) {
        return contestMapper.map(contestRepository.findById(id).orElse(null));
    }

    public List<ContestDTO> findAll() {
        return contestRepository.findAll().stream().map(contestMapper::map).collect(Collectors.toList());
    }
}