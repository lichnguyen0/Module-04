package com.example.md4b6bt1.controller;

import com.example.md4b6bt1.model.Category;
import com.example.md4b6bt1.service.ICategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryRestController {

    private final ICategoryService categoryService;

    public CategoryRestController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Xem danh sách tất cả category
    @GetMapping("/api/categories")
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    // Xem chi tiết một category
    @GetMapping("/api/categories/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.findById(id);
    }
}

