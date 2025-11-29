package com.example.dayphoto.service;


import com.example.dayphoto.model.Feedback;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HibernateFeedbackService implements IFeedbackService {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Feedback> findAllToday() {
        String queryStr = "SELECT f FROM Feedback f WHERE f.date = :today";
        TypedQuery<Feedback> query = entityManager.createQuery(queryStr, Feedback.class);
        query.setParameter("today", LocalDate.now());
        return query.getResultList();
    }

    @Override
    public void save(Feedback feedback) {
        feedback.setDate(LocalDate.now());
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(feedback);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }

    @Override
    public Feedback findById(int id) {
        return entityManager.find(Feedback.class, id);
    }

    @Override
    public void like(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Feedback feedback = session.find(Feedback.class, id);
            if (feedback != null) {
                feedback.setLikes(feedback.getLikes() + 1);
                session.update(feedback);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) transaction.rollback();
        }
    }
}
