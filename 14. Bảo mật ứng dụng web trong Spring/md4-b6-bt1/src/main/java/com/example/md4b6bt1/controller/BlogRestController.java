package com.example.md4b6bt1.controller;

import com.example.md4b6bt1.model.Blog;
import com.example.md4b6bt1.service.IBlogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogRestController {

    private final IBlogService blogService;

    public BlogRestController(IBlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/load")
    public List<Blog> loadBlogs(@RequestParam int offset, @RequestParam int limit) {
        return blogService.findAll().stream()
                .skip(offset)
                .limit(limit)
                .toList();
    }

    @GetMapping("/search")
    public List<Blog> searchBlogs(@RequestParam String keyword) {
        return blogService.findByTitleContaining(keyword);
    }
}
