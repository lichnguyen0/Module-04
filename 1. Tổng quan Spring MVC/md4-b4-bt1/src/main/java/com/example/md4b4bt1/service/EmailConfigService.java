package com.example.md4b4bt1.service;

import com.example.md4b4bt1.model.EmailConfig;
import org.springframework.stereotype.Service;

@Service
public class EmailConfigService {
    private EmailConfig currentConfig = new EmailConfig("English", "25", false, "Thor\nKing, Asgard");

    public EmailConfig getCurrentConfig() {
        return currentConfig;
    }

    public void updateConfig(EmailConfig newConfig) {
        this.currentConfig = newConfig;
    }
}