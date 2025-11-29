package com.example.dayphoto.repository;

import com.example.dayphoto.model.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
    Page<Feedback> findByDate(LocalDate date, Pageable pageable);
}
