package com.example.md4b6bt1.controller;

import com.example.md4b6bt1.model.Blog;
import com.example.md4b6bt1.model.Category;
import com.example.md4b6bt1.service.IBlogService;
import com.example.md4b6bt1.service.ICategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    // --- List blogs with pagination, sort, search, filter by category ---
    @GetMapping
    public String listBlogs(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "createdAt") String sortField,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String keyword
    ) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Blog> blogPage;

        if (keyword != null && !keyword.isEmpty()) {
            List<Blog> list = blogService.findByTitleContaining(keyword);
            blogPage = new org.springframework.data.domain.PageImpl<>(list, pageable, list.size());
        } else if (categoryId != null) {
            List<Blog> list = blogService.findByCategoryId(categoryId);
            blogPage = new org.springframework.data.domain.PageImpl<>(list, pageable, list.size());
        } else {
            blogPage = blogService.findAll(pageable);
        }

        List<Category> categories = categoryService.findAll();

        model.addAttribute("blogPage", blogPage);
        model.addAttribute("categories", categories);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("keyword", keyword);
        return "listBlogs";
    }

    // --- View single blog ---
    @GetMapping("/view")
    public String viewBlog(@RequestParam Long id, Model model) {
        Blog blog = blogService.findById(id);
        model.addAttribute("blog", blog);
        return "viewBlog";
    }

    // --- Show create form ---
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("blog", new Blog());
        model.addAttribute("categories", categoryService.findAll());
        return "formBlog";
    }

    // --- Show edit form ---
    @GetMapping("/edit")
    public String editForm(@RequestParam Long id, Model model) {
        Blog blog = blogService.findById(id);
        model.addAttribute("blog", blog);
        model.addAttribute("categories", categoryService.findAll());
        return "formBlog";
    }

    // --- Save blog: create or update ---
    @PostMapping("/save")
    public String saveBlog(@ModelAttribute Blog blog) {
        // Gán Category từ ID
        if (blog.getCategory() != null && blog.getCategory().getId() != null) {
            Category category = categoryService.findById(blog.getCategory().getId());
            blog.setCategory(category);
        }

        if (blog.getId() == null) {
            blogService.create(blog); // tạo mới
        } else {
            blogService.update(blog); // cập nhật
        }

        return "redirect:/blogs";
    }

    // --- Delete blog ---
    @GetMapping("/delete")
    public String deleteBlog(@RequestParam Long id) {
        blogService.delete(id);
        return "redirect:/blogs";
    }
}
