package com.example.md4b4bt2.controller;

import com.example.md4b4bt2.model.Song;
import com.example.md4b4bt2.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SongController {

    @Autowired
    private SongService songService;

    private final List<String> allowedExtensions = Arrays.asList("mp3", "wav", "ogg", "m4p");

    @Value("${application.upload:./audio_uploads/}")
    private String uploadDir;

    @GetMapping("/upload")
    public String showUploadForm(Model model) {
        model.addAttribute("song", new Song());
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadSong(@ModelAttribute Song song, Model model) throws IOException {
        MultipartFile file = song.getFile();

        if (file == null || file.isEmpty()) {
            model.addAttribute("error", "Bạn phải chọn file.");
            return "upload";
        }

        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        // KIỂM TRA TÊN FILE
        if (filename.contains("..")) {
            model.addAttribute("error", "Tên file không hợp lệ!");
            return "upload";
        }

        String ext = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();

        if (!allowedExtensions.contains(ext)) {
            model.addAttribute("error", "Chỉ chấp nhận file: .mp3, .wav, .ogg, .m4p");
            return "upload";
        }

        // TẠO THƯ MỤC VÀ KIỂM TRA QUYỀN
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
                System.out.println("Đã tạo thư mục: " + uploadPath.toAbsolutePath());
            } catch (IOException e) {
                model.addAttribute("error", "Không thể tạo thư mục: " + e.getMessage());
                return "upload";
            }
        }

        // KIỂM TRA QUYỀN GHI
        if (!Files.isWritable(uploadPath)) {
            model.addAttribute("error", "Không có quyền ghi vào thư mục: " + uploadDir);
            return "upload";
        }

        // UPLOAD FILE
        try {
            Path filePath = uploadPath.resolve(filename);
            file.transferTo(filePath.toFile());
            System.out.println("Upload thành công: " + filePath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Lỗi upload: " + e.getMessage());
            model.addAttribute("error", "Lỗi upload file: " + e.getMessage());
            return "upload";
        }

        // XỬ LÝ THỂ LOẠI
        if (song.getGenresStr() != null && !song.getGenresStr().isEmpty()) {
            List<String> list = Arrays.stream(song.getGenresStr().split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
            song.setGenres(list);
        }

        song.setFile(null);
        songService.save(song);

        return "redirect:/songs";
    }

    @GetMapping("/songs")
    public String listSongs(Model model) {
        model.addAttribute("songs", songService.findAll());
        return "list";
    }
}