package com.example.md4b6bt1.controller;

import com.example.md4b6bt1.model.Blog;
import com.example.md4b6bt1.service.IBlogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlogRestController {

    private final IBlogService blogService;

    public BlogRestController(IBlogService blogService) {
        this.blogService = blogService;
    }

    // Xem danh sách tất cả blog
        @GetMapping("/api/blogs")
    public List<Blog> getAllBlogs() {
        return blogService.findAll();
    }

    // Xem chi tiết một blog
    @GetMapping("/api/blogs/{id}")
    public Blog getBlogById(@PathVariable Long id) {
        return blogService.findById(id);
    }

    // Xem danh sách blog theo category
    @GetMapping("/api/blogs/category/{categoryId}")
    public List<Blog> getBlogsByCategory(@PathVariable Long categoryId) {
        return blogService.findByCategoryId(categoryId);
    }

    // Tìm kiếm blog theo tiêu đề
    @GetMapping("/api/blogs/search")
    public List<Blog> searchBlogs(@RequestParam String keyword) {
        return blogService.findByTitleContaining(keyword);
    }
}
