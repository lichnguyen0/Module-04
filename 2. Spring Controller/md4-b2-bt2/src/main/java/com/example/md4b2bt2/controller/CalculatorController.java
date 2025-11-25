package com.example.md4b2bt2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @GetMapping("/")
    public String showCalculator() {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(
            @RequestParam("firstOperand") double firstOperand,
            @RequestParam("secondOperand") double secondOperand,
            @RequestParam("operator") String operator,
            Model model) {

        double result;
        String operation = "";

        try {
            switch (operator) {
                case "add":
                    result = firstOperand + secondOperand;
                    operation = "Cộng";
                    break;
                case "subtract":
                    result = firstOperand - secondOperand;
                    operation = "Trừ";
                    break;
                case "multiply":
                    result = firstOperand * secondOperand;
                    operation = "Nhân";
                    break;
                case "divide":
                    if (secondOperand == 0) {
                        model.addAttribute("error", "Không thể chia cho 0!");
                        model.addAttribute("firstOperand", firstOperand);
                        model.addAttribute("secondOperand", secondOperand);
                        model.addAttribute("operator", operator);
                        return "index";
                    }
                    result = firstOperand / secondOperand;
                    operation = "Chia";
                    break;
                default:
                    model.addAttribute("error", "Phép tính không hợp lệ!");
                    return "index";
            }

            model.addAttribute("firstOperand", firstOperand);
            model.addAttribute("secondOperand", secondOperand);
            model.addAttribute("operator", operator);
            model.addAttribute("operation", operation);
            model.addAttribute("result", result);

        } catch (Exception e) {
            model.addAttribute("error", "Lỗi tính toán: " + e.getMessage());
        }

        return "index";
    }
}
