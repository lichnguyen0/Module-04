package com.example.dayphoto.service;

import com.example.dayphoto.model.Feedback;
import com.example.dayphoto.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JpaFeedbackService implements IFeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public List<Feedback> findAllToday() {
        return feedbackRepository.findAllToday();
    }

    @Override
    public void save(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    @Override
    public Feedback findById(int id) {
        return feedbackRepository.findById(id);
    }

    @Override
    public void like(int id) {
        feedbackRepository.like(id);
    }
}
