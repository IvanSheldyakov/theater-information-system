package com.db.theaterinformationsystem.service;

import com.db.theaterinformationsystem.dto.CategoryDTO;
import com.db.theaterinformationsystem.mappers.CommonMapper;
import com.db.theaterinformationsystem.model.Category;
import com.db.theaterinformationsystem.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CommonMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long save(CategoryDTO dto) {
        Category category = categoryMapper.map(dto);
        Category newCategory = categoryRepository.save(category);
        return newCategory.getId();
    }

    public CategoryDTO find(Long id) {
        return categoryMapper.map(categoryRepository.findById(id).orElse(null));
    }

    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream().map(categoryMapper::map).collect(Collectors.toList());
    }
}