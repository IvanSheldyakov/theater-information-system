package com.db.theaterinformationsystem.service;

import com.db.theaterinformationsystem.dto.GenreDTO;
import com.db.theaterinformationsystem.exception.ExceptionSupplier;
import com.db.theaterinformationsystem.mappers.PlayMapper;
import com.db.theaterinformationsystem.model.Genre;
import com.db.theaterinformationsystem.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final PlayMapper genreMapper;
    private final GenreRepository genreRepository;

    @Transactional
    public Long save(GenreDTO dto) {
        Genre genre = genreMapper.map(dto);
        Genre newGenre = genreRepository.save(genre);
        return newGenre.getId();
    }

    public GenreDTO find(Long id) {
        return genreMapper.map(genreRepository.findById(id).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND));
    }

    public List<GenreDTO> findAll() {
        return genreRepository.findAll().stream().map(genreMapper::map).collect(Collectors.toList());
    }
}
