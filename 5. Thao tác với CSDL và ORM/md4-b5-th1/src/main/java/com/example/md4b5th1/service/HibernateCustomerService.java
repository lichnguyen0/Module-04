package com.example.md4b5th1.service;

import com.example.md4b5th1.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HibernateCustomerService implements ICustomerService {
    private static EntityManager entityManager;
    private static SessionFactory sessionFactory;

    static {
        try {
            // SỬA: Thêm try-catch chi tiết và khởi tạo rõ ràng
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
            System.out.println("Hibernate initialized successfully"); // Debug log
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Failed to initialize Hibernate: " + e.getMessage());
        }
    }

    @Override
    public List<Customer> findAll() {
        // SỬA: Kiểm tra entityManager trước khi sử dụng
        if (entityManager == null) {
            throw new IllegalStateException("EntityManager is not initialized");
        }

        String queryStr = "SELECT c FROM Customer AS c";
        TypedQuery<Customer> query = entityManager.createQuery(queryStr, Customer.class);
        return query.getResultList();
    }

    @Override
    public void save(Customer customer) {
        // SỬA: Kiểm tra sessionFactory trước khi sử dụng
        if (sessionFactory == null) {
            throw new IllegalStateException("SessionFactory is not initialized");
        }

        Transaction transaction = null;
        Customer origin;
        if (customer.getId() == 0) {
            origin = new Customer();
        } else {
            origin = findById(customer.getId());
        }
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            origin.setName(customer.getName());
            origin.setEmail(customer.getEmail());
            origin.setAddress(customer.getAddress());
            session.saveOrUpdate(origin);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Customer findById(int id) {
        // SỬA: Kiểm tra entityManager trước khi sử dụng
        if (entityManager == null) {
            throw new IllegalStateException("EntityManager is not initialized");
        }

        String queryStr = "SELECT c FROM Customer AS c WHERE c.id = :id";
        TypedQuery<Customer> query = entityManager.createQuery(queryStr, Customer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void remove(int id) {
        // SỬA: Kiểm tra sessionFactory trước khi sử dụng
        if (sessionFactory == null) {
            throw new IllegalStateException("SessionFactory is not initialized");
        }

        Customer customer = findById(id);
        if (customer != null) {
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.remove(customer);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }

    // SỬA: Thêm phương thức kiểm tra trạng thái khởi tạo
    public boolean isInitialized() {
        return entityManager != null && sessionFactory != null;
    }
}