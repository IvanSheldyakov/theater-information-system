package com.db.theaterinformationsystem.service;

import com.db.theaterinformationsystem.dto.ActorDTO;
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

    @Transactional
    public Long save(ActorDTO dto) {

        Actor actor = actorMapper.map(dto);
        Actor newActor = actorRepository.save(actor);
        return newActor.getId();
    }

    public ActorDTO find(Long id) {
        return actorMapper.map(actorRepository.findById(id).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND));
    }

    public List<ActorDTO> findAll() {
        return actorRepository.findAll().stream().map(actorMapper::map).collect(Collectors.toList());
    }
}
