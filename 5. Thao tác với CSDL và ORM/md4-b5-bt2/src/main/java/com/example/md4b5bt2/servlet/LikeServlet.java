package com.example.md4b5bt2.servlet;

import com.example.md4b5bt2.service.FeedbackService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/like")
public class LikeServlet extends HttpServlet {
    private final FeedbackService feedbackService = new FeedbackService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        try {
            Long feedbackId = Long.parseLong(req.getParameter("feedbackId"));
            feedbackService.likeFeedback(feedbackId);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Error liking feedback");
        }
    }

    @Override
    public void destroy() {
        feedbackService.cleanup();
        super.destroy();
    }
}
