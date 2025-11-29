package com.example.dayphoto.service;

import com.example.dayphoto.model.Feedback;
import com.example.dayphoto.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class JpaFeedbackService implements IFeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public Page<Feedback> findAllTodayPaged(int page, int size) {
        return feedbackRepository.findByDate(LocalDate.now(), PageRequest.of(page, size));
    }

    @Override
    public void save(Feedback feedback) {
        feedback.setDate(LocalDate.now());
        feedbackRepository.save(feedback);
    }

    @Override
    public Feedback findById(int id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    @Override
    public void like(int id) {
        Feedback feedback = findById(id);
        if (feedback != null) {
            feedback.setLikes(feedback.getLikes() + 1);
            feedbackRepository.save(feedback);
        }
    }
}
