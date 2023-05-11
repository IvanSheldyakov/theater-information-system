package com.db.theaterinformationsystem.service;

import com.db.theaterinformationsystem.dto.AuthorDTO;
import com.db.theaterinformationsystem.exception.ExceptionSupplier;
import com.db.theaterinformationsystem.mappers.AuthorMapper;
import com.db.theaterinformationsystem.model.Author;
import com.db.theaterinformationsystem.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorMapper authorMapper;
    private final AuthorRepository authorRepository;

    @Transactional
    public Long save(AuthorDTO dto) {
        Author author = authorMapper.map(dto);
        Author newAuthor = authorRepository.save(author);
        return newAuthor.getId();
    }

    public AuthorDTO find(Long id) {
        return authorMapper.map(authorRepository.findById(id).orElseThrow(ExceptionSupplier.DATA_NOT_FOUND));
    }

    public List<AuthorDTO> findAll() {
        return authorRepository.findAll().stream().map(authorMapper::map).collect(Collectors.toList());
    }
}
