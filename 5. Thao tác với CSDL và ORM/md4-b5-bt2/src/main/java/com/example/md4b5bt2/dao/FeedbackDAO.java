package com.example.md4b5bt2.dao;

import com.example.md4b5bt2.entity.Feedback;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.time.LocalDate;
import java.util.List;

public class FeedbackDAO {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public void saveFeedback(Feedback feedback) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(feedback);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public List<Feedback> getFeedbacksByDate(LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "FROM Feedback WHERE createdDate = :date ORDER BY createdAt DESC", Feedback.class)
                    .setParameter("date", date)
                    .getResultList();
        }
    }

    public void incrementLikeCount(Long feedbackId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Feedback feedback = session.get(Feedback.class, feedbackId);
            if (feedback != null) {
                feedback.setLikeCount(feedback.getLikeCount() + 1);
                session.merge(feedback);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public static void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}