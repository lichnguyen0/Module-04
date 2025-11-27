package com.example.md4b6bt1.service;



import com.example.md4b6bt1.model.Blog;
import java.util.List;

public interface IBlogService {
    void create(Blog blog);
    void update(Blog blog);
    void delete(Long id);
    Blog findById(Long id);
    List<Blog> findAll();
}
