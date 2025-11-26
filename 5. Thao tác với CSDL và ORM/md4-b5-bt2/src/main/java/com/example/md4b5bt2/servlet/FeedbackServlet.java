package com.example.md4b5bt2.servlet;

import com.example.md4b5bt2.service.FeedbackService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/feedback")
public class FeedbackServlet extends HttpServlet {
    private final FeedbackService feedbackService = new FeedbackService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String authorName = req.getParameter("authorName");
            String comment = req.getParameter("comment");
            Integer rating = Integer.parseInt(req.getParameter("rating"));

            feedbackService.addFeedback(authorName, comment, rating);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Error saving feedback");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            var feedbacks = feedbackService.getTodayFeedbacks();
            resp.setContentType("application/json");
            objectMapper.writeValue(resp.getWriter(), feedbacks);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error retrieving feedbacks");
        }
    }

    @Override
    public void destroy() {
        feedbackService.cleanup();
        super.destroy();
    }
}