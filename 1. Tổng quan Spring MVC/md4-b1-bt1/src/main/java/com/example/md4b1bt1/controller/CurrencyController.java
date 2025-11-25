package com.example.md4b1bt1.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CurrencyController {

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @PostMapping("/convert")
    public String convertCurrency(
            @RequestParam("rate") double rate,
            @RequestParam("usd") double usd,
            Model model) {

        double vnd = usd * rate;

        model.addAttribute("rate", rate);
        model.addAttribute("usd", usd);
        model.addAttribute("vnd", vnd);

        return "result";
    }
}
