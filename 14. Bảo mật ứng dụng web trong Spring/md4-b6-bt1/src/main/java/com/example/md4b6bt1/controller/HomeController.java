package com.example.md4b6bt1.controller;

import com.example.md4b6bt1.model.Blog;
import com.example.md4b6bt1.service.IBlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final IBlogService blogService;

    public HomeController(IBlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Blog> blogs = blogService.findAll();
        model.addAttribute("blogs", blogs);
        return "listBlogs"; // trả về listBlogs.html
    }
}
