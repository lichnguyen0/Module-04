package com.example.dayphoto.service;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProfanityFilter {

    private final List<String> bannedWords = Arrays.asList(
            "badword1",
            "badword2",
            "badword3",
            "ngu",
            "NGU",
            "Ngu",
            "Fu!k",
            "Cực tệ",
            "shit"
            // thêm các từ cấm tại đây

    );

    public boolean containsBannedWords(String text) {
        if (text == null) return false;
        return bannedWords.stream().anyMatch(word -> text.toLowerCase().contains(word.toLowerCase()));
    }
}
