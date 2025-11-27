package com.example.md4b6bt1.repository;


import com.example.md4b6bt1.model.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Repository
public class BlogRepository implements IBlogRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Blog blog) { em.persist(blog); }

    @Override
    public void update(Blog blog) { em.merge(blog); }

    @Override
    public void delete(Long id) {
        Blog blog = em.find(Blog.class, id);
        if (blog != null) em.remove(blog);
    }

    @Override
    public Blog findById(Long id) { return em.find(Blog.class, id); }

    @Override
    public List<Blog> findAll() {
        return em.createQuery("SELECT b FROM Blog b ORDER BY b.createdAt DESC", Blog.class)
                .getResultList();
    }
}
