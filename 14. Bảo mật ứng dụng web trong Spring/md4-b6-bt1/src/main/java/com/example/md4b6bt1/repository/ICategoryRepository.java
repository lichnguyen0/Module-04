package com.example.md4b6bt1.repository;

import com.example.md4b6bt1.model.Category;
import java.util.List;

public interface ICategoryRepository {
    void save(Category category);
    void update(Category category);
    void delete(Long id);
    Category findById(Long id);
    List<Category> findAll();
}
