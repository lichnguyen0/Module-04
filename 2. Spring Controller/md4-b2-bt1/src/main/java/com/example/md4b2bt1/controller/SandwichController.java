package com.example.md4b2bt1.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;




@Controller
public class SandwichController {

    private final List<String> condiments = Arrays.asList(
            "Lettuce", "Tomato", "Mustard", "Sprouts",
            "Mayonnaise", "Ketchup", "Onion", "Pickles"
    );

    @GetMapping("/")
    public String home(Model model) {
        System.out.println("=== HOME CONTROLLER CALLED ===");
        System.out.println("Condiments: " + condiments);
        model.addAttribute("condiments", condiments);
        model.addAttribute("title", "Sandwich Condiments");
        return "index";
    }

    @PostMapping("/save")
    public String save(
            @RequestParam(value = "condiment", required = false) String[] selectedCondiments,
            Model model) {

        System.out.println("=== SAVE CONTROLLER CALLED ===");
        System.out.println("Selected: " + Arrays.toString(selectedCondiments));

        model.addAttribute("condiments", condiments);

        if (selectedCondiments == null || selectedCondiments.length == 0) {
            model.addAttribute("message", "No condiments selected!");
        } else {
            model.addAttribute("selectedCondiments", selectedCondiments);
            model.addAttribute("message", "Your selected condiments:");
        }

        return "result";
    }
}