package com.example.md4b6bt1.controller;

import com.example.md4b6bt1.model.Blog;
import com.example.md4b6bt1.service.IBlogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlogRestController {

    private final IBlogService blogService;

    public BlogRestController(IBlogService blogService) {
        this.blogService = blogService;
    }

    // Tìm kiếm blog theo tiêu đề (AJAX)
    @GetMapping("/api/blogs/search")
    public List<Blog> searchBlogs(@RequestParam String keyword) {
        return blogService.findByTitleContaining(keyword);
    }

    // Lấy danh sách blog theo offset + limit (AJAX tải thêm)
    @GetMapping("/api/blogs/load")
    public List<Blog> loadMoreBlogs(@RequestParam int offset, @RequestParam int limit) {
        List<Blog> allBlogs = blogService.findAll();
        int fromIndex = Math.min(offset, allBlogs.size());
        int toIndex = Math.min(offset + limit, allBlogs.size());
        return allBlogs.subList(fromIndex, toIndex);
    }
}
