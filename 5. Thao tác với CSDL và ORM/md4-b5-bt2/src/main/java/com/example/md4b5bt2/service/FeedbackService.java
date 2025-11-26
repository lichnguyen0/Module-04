package com.example.md4b5bt2.service;
import com.example.md4b5bt2.dao.FeedbackDAO; // SỬA IMPORT NÀY
import com.example.md4b5bt2.entity.Feedback;

import java.time.LocalDate;
import java.util.List;

public class FeedbackService {
    private final FeedbackDAO feedbackDAO = new FeedbackDAO();

    public void addFeedback(String authorName, String comment, Integer rating) {
        Feedback feedback = new Feedback();
        feedback.setAuthorName(authorName);
        feedback.setComment(comment);
        feedback.setRating(rating);
        feedbackDAO.saveFeedback(feedback);
    }

    public List<Feedback> getTodayFeedbacks() {
        return feedbackDAO.getFeedbacksByDate(LocalDate.now());
    }

    public void likeFeedback(Long feedbackId) {
        feedbackDAO.incrementLikeCount(feedbackId);
    }

    public void cleanup() {
        feedbackDAO.close();
    }
}