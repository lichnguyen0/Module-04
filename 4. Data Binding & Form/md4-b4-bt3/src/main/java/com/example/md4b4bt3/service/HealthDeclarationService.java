package com.example.md4b4bt3.service;

import com.example.md4b4bt3.model.HealthDeclaration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HealthDeclarationService {
    private List<HealthDeclaration> declarations = new ArrayList<>();

    public void save(HealthDeclaration declaration) {
        // Simple implementation - in real app, you'd update existing or add new
        declarations.clear(); // Only keep one declaration for demo
        declarations.add(declaration);
    }

    public HealthDeclaration getLatest() {
        return declarations.isEmpty() ? null : declarations.get(0);
    }

    public List<HealthDeclaration> getAll() {
        return new ArrayList<>(declarations);
    }
}