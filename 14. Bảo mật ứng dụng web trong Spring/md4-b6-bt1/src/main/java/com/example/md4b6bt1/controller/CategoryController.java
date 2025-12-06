package com.example.md4b6bt1.controller;

import com.example.md4b6bt1.model.Category;
import com.example.md4b6bt1.service.ICategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "listCategories";
    }

    @GetMapping("/create")
    public String formCreate(Model model) {
        model.addAttribute("category", new Category());
        return "formCategory";
    }

    @PostMapping("/create")
    public String createCategory(@ModelAttribute Category category) {
        categoryService.create(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit")
    public String formEdit(@RequestParam Long id, Model model) {
        model.addAttribute("category", categoryService.findById(id));
        return "formCategory";
    }

    @PostMapping("/edit")
    public String updateCategory(@ModelAttribute Category category) {
        categoryService.update(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete")
    public String deleteCategory(@RequestParam Long id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }
}
