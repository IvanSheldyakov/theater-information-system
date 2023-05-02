package com.db.theaterinformationsystem.service;

import com.db.theaterinformationsystem.dto.PlayDTO;
import com.db.theaterinformationsystem.mappers.PlayMapper;
import com.db.theaterinformationsystem.model.Play;
import com.db.theaterinformationsystem.repository.PlayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayService {

    private final PlayMapper playMapper;
    private final PlayRepository playRepository;

    @Transactional
    public Long save(PlayDTO dto) {
        Play play = playMapper.map(dto);
        Play newPlay = playRepository.save(play);
        return newPlay.getId();
    }

    public PlayDTO find(Long id) {
        return playMapper.map(playRepository.findById(id).orElse(null));
    }

    public List<PlayDTO> findAll() {
        return playRepository.findAll().stream().map(playMapper::map).collect(Collectors.toList());
    }
}
