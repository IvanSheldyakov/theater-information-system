package com.db.theaterinformationsystem.service;

import com.db.theaterinformationsystem.dto.ActorContestDTO;
import com.db.theaterinformationsystem.dto.ContestDTO;
import com.db.theaterinformationsystem.exception.ExceptionSupplier;
import com.db.theaterinformationsystem.mappers.ContestMapper;
import com.db.theaterinformationsystem.model.Actor;
import com.db.theaterinformationsystem.model.ActorContest;
import com.db.theaterinformationsystem.model.Contest;
import com.db.theaterinformationsystem.repository.ActorContestRepository;
import com.db.theaterinformationsystem.repository.ActorRepository;
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
    private final ActorRepository actorRepository;
    private final ActorContestRepository actorContestRepository;

    @Transactional
    public Long save(ContestDTO dto) {
        Contest contest = contestMapper.map(dto);
        Contest newContest = contestRepository.save(contest);
        return newContest.getId();
    }

    public ContestDTO find(Long id) {
        return contestMapper.map(contestRepository.findById(id).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND));
    }

    public List<ContestDTO> findAll() {
        return contestRepository.findAll().stream().map(contestMapper::map).collect(Collectors.toList());
    }

    @Transactional
    public void addActorToContest(ActorContestDTO dto) {
        Actor actor = actorRepository.findById(dto.getActorId()).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        Contest contest = contestRepository.findById(dto.getContestId()).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND);
        ActorContest actorContest = new ActorContest();
        actorContest.setActor(actor);
        actorContest.setContest(contest);
        actorContest.setWinner(dto.getWinner());
        actorContestRepository.save(actorContest);
    }
}