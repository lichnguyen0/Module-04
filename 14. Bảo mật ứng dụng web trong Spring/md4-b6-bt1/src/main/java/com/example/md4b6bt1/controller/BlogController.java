package com.example.md4b6bt1.controller;

import com.example.md4b6bt1.model.Blog;
import com.example.md4b6bt1.model.Category;
import com.example.md4b6bt1.service.IBlogService;
import com.example.md4b6bt1.service.ICategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    private final IBlogService blogService;
    private final ICategoryService categoryService;

    public BlogController(IBlogService blogService, ICategoryService categoryService) {
        this.blogService = blogService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String listBlogs(Model model) {
        model.addAttribute("blogs", blogService.findAll());
        return "listBlogs";
    }

    @GetMapping("/create")
    public String formCreate(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("categories", categoryService.findAll());
        return "formBlog";
    }

    @PostMapping("/save")
    public String saveBlog(@ModelAttribute Blog blog) {
        if(blog.getId() == null) blogService.create(blog);
        else blogService.update(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/edit")
    public String formEdit(@RequestParam Long id, Model model) {
        Blog blog = blogService.findById(id);
        model.addAttribute("blog", blog);
        model.addAttribute("categories", categoryService.findAll());
        return "formBlog";
    }

    @GetMapping("/delete")
    public String deleteBlog(@RequestParam Long id) {
        blogService.delete(id);
        return "redirect:/blogs";
    }

    @GetMapping("/view")
    public String viewBlog(@RequestParam Long id, Model model) {
        model.addAttribute("blog", blogService.findById(id));
        return "viewBlog";
    }
}
