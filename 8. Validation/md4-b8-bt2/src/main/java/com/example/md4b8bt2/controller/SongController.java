package com.example.md4b8bt2.controller;


import com.example.md4b8bt2.model.Song;
import com.example.md4b8bt2.service.ISongService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SongController {

    @Autowired
    private ISongService songService;

    @GetMapping("/songs/add")
    public String showForm(Model model) {
        model.addAttribute("song", new Song());
        return "songForm";
    }

    @PostMapping("/songs/add")
    public String submitForm(@Valid @ModelAttribute("song") Song song,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "songForm";
        }
        songService.save(song);
        return "songResult";
    }
}
