package com.example.dayphoto.service;

import com.example.dayphoto.model.Feedback;

import java.util.List;

public interface IFeedbackService {
    List<Feedback> findAllToday();
    void save(Feedback feedback);
    Feedback findById(int id);
    void like(int id);
}
