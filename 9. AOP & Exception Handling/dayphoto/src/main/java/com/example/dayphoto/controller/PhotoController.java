package com.example.dayphoto.controller;

import com.example.dayphoto.model.Feedback;
import com.example.dayphoto.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    private IFeedbackService feedbackService;

    @GetMapping("")
    public String index(Model model,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "5") int size) {
        var feedbackPage = feedbackService.findAllTodayPaged(page, size);
        model.addAttribute("feedbacks", feedbackPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", feedbackPage.getTotalPages());
        model.addAttribute("pageSize", size);
        return "index";
    }

    @PostMapping("/feedback")
    public String saveFeedback(@RequestParam String author,
                               @RequestParam String comment,
                               @RequestParam int rating) {
        Feedback feedback = new Feedback(author, comment, rating, null);
        feedbackService.save(feedback);
        return "redirect:/photo";
    }

    @PostMapping("/like/{id}")
    public String likeFeedback(@PathVariable int id) {
        feedbackService.like(id);
        return "redirect:/photo";
    }
}
