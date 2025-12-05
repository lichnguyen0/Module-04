package com.example.md4b6bt1.repository;

import com.example.md4b6bt1.model.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CategoryRepository implements ICategoryRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Category category) { em.persist(category); }

    @Override
    public void update(Category category) { em.merge(category); }

    @Override
    public void delete(Long id) {
        Category cat = em.find(Category.class, id);
        if (cat != null) em.remove(cat);
    }

    @Override
    public Category findById(Long id) { return em.find(Category.class, id); }

    @Override
    public List<Category> findAll() {
        return em.createQuery("SELECT c FROM Category c ORDER BY c.name", Category.class)
                .getResultList();
    }
}
