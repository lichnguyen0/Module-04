package com.example.dayphoto.service;

import com.example.dayphoto.model.Feedback;
import org.springframework.data.domain.Page;

public interface IFeedbackService {
    Page<Feedback> findAllTodayPaged(int page, int size);
    void save(Feedback feedback);
    Feedback findById(int id);
    void like(int id);
}
