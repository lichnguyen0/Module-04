package com.example.md4b6bt1.service;


import com.example.md4b6bt1.model.Blog;
import com.example.md4b6bt1.repository.IBlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;




import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BlogService implements IBlogService {

    private final IBlogRepository blogRepository;

    public BlogService(IBlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

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
}
