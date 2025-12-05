package com.example.md4b6bt1.service;

import com.example.md4b6bt1.model.Category;
import com.example.md4b6bt1.repository.ICategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void create(Category category) { categoryRepository.save(category); }

    @Override
    public void update(Category category) { categoryRepository.update(category); }

    @Override
    public void delete(Long id) { categoryRepository.delete(id); }

    @Override
    public Category findById(Long id) { return categoryRepository.findById(id); }

    @Override
    public List<Category> findAll() { return categoryRepository.findAll(); }
}
