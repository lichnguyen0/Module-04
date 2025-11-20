package com.example.md4b4bt1.controller;

import com.example.md4b4bt1.model.EmailConfig;
import com.example.md4b4bt1.service.EmailConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmailConfigController {

    @Autowired
    private EmailConfigService emailConfigService;

    @GetMapping("/")
    public String home() {
        return "redirect:/config";
    }

    @GetMapping("/config")
    public String showConfig(Model model) {
        model.addAttribute("emailConfig", emailConfigService.getCurrentConfig());
        return "email-config";
    }

    @PostMapping("/config")
    public String updateConfig(@ModelAttribute EmailConfig emailConfig) {
        emailConfigService.updateConfig(emailConfig);
        return "redirect:/config";
    }
}