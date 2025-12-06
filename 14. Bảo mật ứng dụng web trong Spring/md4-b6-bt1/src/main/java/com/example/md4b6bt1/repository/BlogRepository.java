package com.example.md4b6bt1.repository;

import com.example.md4b6bt1.model.Blog;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        if(blog != null) em.remove(blog);
    }

    @Override
    public Blog findById(Long id) { return em.find(Blog.class, id); }

    @Override
    public List<Blog> findAll() {
        return em.createQuery("SELECT b FROM Blog b ORDER BY b.createdAt DESC", Blog.class)
                .getResultList();
    }

    @Override
    public List<Blog> findByTitleContaining(String keyword) {
        return em.createQuery("SELECT b FROM Blog b WHERE b.title LIKE :kw ORDER BY b.createdAt DESC", Blog.class)
                .setParameter("kw", "%" + keyword + "%")
                .getResultList();
    }

    @Override
    public List<Blog> findByCategoryId(Long categoryId) {
        return em.createQuery("SELECT b FROM Blog b WHERE b.category.id = :cid ORDER BY b.createdAt DESC", Blog.class)
                .setParameter("cid", categoryId)
                .getResultList();
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        List<Blog> blogs = em.createQuery("SELECT b FROM Blog b ORDER BY b.createdAt DESC", Blog.class)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
        return new PageImpl<>(blogs, pageable, blogs.size());
    }
}
