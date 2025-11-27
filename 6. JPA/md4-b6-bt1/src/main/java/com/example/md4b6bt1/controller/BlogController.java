package com.example.md4b6bt1.controller;

import com.example.md4b6bt1.model.Blog;
import com.example.md4b6bt1.service.IBlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    private final IBlogService blogService;

    public BlogController(IBlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public String listBlogs(Model model) {
        List<Blog> blogs = blogService.findAll();
        model.addAttribute("blogs", blogs);
        return "listBlogs";
    }

    @GetMapping("/view")
    public String viewBlog(@RequestParam Long id, Model model) {
        Blog blog = blogService.findById(id);
        model.addAttribute("blog", blog);
        return "viewBlog";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("blog", new Blog());
        return "formBlog";
    }

    @PostMapping("/create")
    public String createBlog(@ModelAttribute Blog blog) {
        blogService.create(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam Long id, Model model) {
        Blog blog = blogService.findById(id);
        model.addAttribute("blog", blog);
        return "formBlog";
    }

    @PostMapping("/edit")
    public String editBlog(@ModelAttribute Blog blog) {
        blogService.update(blog);
        return "redirect:/blogs";
    }

    @GetMapping("/delete")
    public String deleteBlog(@RequestParam Long id) {
        blogService.delete(id);
        return "redirect:/blogs";
    }
}