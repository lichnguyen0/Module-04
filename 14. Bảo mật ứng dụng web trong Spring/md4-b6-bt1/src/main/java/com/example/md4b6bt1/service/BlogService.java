package com.example.md4b6bt1.service;

import com.example.md4b6bt1.model.Blog;
import com.example.md4b6bt1.repository.IBlogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class BlogService implements IBlogService {

    private final IBlogRepository blogRepository;

    public BlogService(IBlogRepository blogRepository) { this.blogRepository = blogRepository; }

    @Override
    public void create(Blog blog) { blogRepository.save(blog); }

    @Override
    public void update(Blog blog) { blogRepository.update(blog); }

    @Override
    public void delete(Long id) { blogRepository.delete(id); }

    @Override
    public Blog findById(Long id) { return blogRepository.findById(id); }

    @Override
    public List<Blog> findAll() { return blogRepository.findAll(); }

    @Override
    public List<Blog> findByTitleContaining(String keyword) { return blogRepository.findByTitleContaining(keyword); }

    @Override
    public List<Blog> findByCategoryId(Long categoryId) { return blogRepository.findByCategoryId(categoryId); }

    @Override
    public Page<Blog> findAll(Pageable pageable) { return blogRepository.findAll(pageable); }
}
