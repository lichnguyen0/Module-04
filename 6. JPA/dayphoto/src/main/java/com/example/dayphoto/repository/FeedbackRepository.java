package com.example.dayphoto.repository;

import com.example.dayphoto.model.Feedback;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public class FeedbackRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Feedback> findAllToday() {
        TypedQuery<Feedback> query = em.createQuery(
                "SELECT f FROM Feedback f WHERE f.date = :today", Feedback.class);
        query.setParameter("today", LocalDate.now());
        return query.getResultList();
    }

    public void save(Feedback feedback) {
        feedback.setDate(LocalDate.now());
        em.persist(feedback);
    }

    public Feedback findById(int id) {
        return em.find(Feedback.class, id);
    }

    public void like(int id) {
        Feedback feedback = em.find(Feedback.class, id);
        if (feedback != null) {
            feedback.setLikes(feedback.getLikes() + 1);
            em.merge(feedback);
        }
    }
}
