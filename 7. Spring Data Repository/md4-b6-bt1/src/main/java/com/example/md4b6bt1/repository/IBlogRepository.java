package com.example.md4b6bt1.repository;

import com.example.md4b6bt1.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBlogRepository {
    void save(Blog blog);
    void update(Blog blog);
    void delete(Long id);
    Blog findById(Long id);
    List<Blog> findAll();
    List<Blog> findByTitleContaining(String keyword);
    List<Blog> findByCategoryId(Long categoryId);
    Page<Blog> findAll(Pageable pageable);
}

