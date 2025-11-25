package com.example.md4b1bt2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DictionaryController {

    // Tạo từ điển Anh-Việt
    private static final Map<String, String> dictionary = new HashMap<>();

    static {
        dictionary.put("hello", "Xin chào");
        dictionary.put("computer", "Máy tính");
        dictionary.put("book", "Quyển sách");
        dictionary.put("water", "Nước");
        dictionary.put("sun", "Mặt trời");
        dictionary.put("moon", "Mặt trăng");
        dictionary.put("house", "Ngôi nhà");
        dictionary.put("school", "Trường học");
        dictionary.put("student", "Học sinh");
        dictionary.put("teacher", "Giáo viên");
        dictionary.put("love", "Yêu thương");
        dictionary.put("friend", "Bạn bè");
        dictionary.put("family", "Gia đình");
        dictionary.put("food", "Thức ăn");
        dictionary.put("music", "Âm nhạc");
    }

    @GetMapping("/")
    public String showDictionaryForm() {
        return "index";
    }

    @PostMapping("/search")
    public String searchWord(
            @RequestParam("englishWord") String englishWord,
            Model model) {

        String lowercaseWord = englishWord.toLowerCase().trim();
        String vietnameseMeaning = dictionary.get(lowercaseWord);

        model.addAttribute("englishWord", englishWord);
        model.addAttribute("vietnameseMeaning", vietnameseMeaning);
        model.addAttribute("wordFound", vietnameseMeaning != null);

        return "result";
    }
}
