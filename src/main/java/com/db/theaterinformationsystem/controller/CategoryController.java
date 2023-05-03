package com.db.theaterinformationsystem.controller;

import com.db.theaterinformationsystem.dto.CategoryDTO;
import com.db.theaterinformationsystem.repository.CategoryRepository;
import com.db.theaterinformationsystem.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
@RequiredArgsConstructor
@Tag(name = "Категории")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody CategoryDTO categoryDTO) {
        Long id = categoryService.save(categoryDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestBody CategoryDTO categoryDTO) {
        categoryService.save(categoryDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/by/id")
    public CategoryDTO find(@RequestParam Long id) {
        return categoryService.find(id);
    }

    @GetMapping("/find/all")
    public List<CategoryDTO> findAll() {
        return categoryService.findAll();
    }

    @DeleteMapping("/delete/by/id")
    public ResponseEntity<HttpStatus> delete(@RequestParam Long id) {
        categoryRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
