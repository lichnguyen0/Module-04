package com.example.md4b5bt1.controller;

import com.example.md4b5bt1.model.Song;
import com.example.md4b5bt1.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private ISongService songService;

    private final String UPLOAD_DIR = "C:/Users/DungG/Downloads/music-ytdlp/uploads/";

    @GetMapping
    public String list(Model model) {
        try {
            List<Song> songs = songService.findAll();
            model.addAttribute("songs", songs);
            return "list";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Lỗi load danh sách: " + e.getMessage());
            return "list";
        }
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("song", new Song());
        return "create";
    }

    @PostMapping("/save")
    public String save(@RequestParam("name") String name,
                       @RequestParam("artist") String artist,
                       @RequestParam("genre") String genre,
                       @RequestParam("file") MultipartFile file,
                       Model model) {
        try {
            System.out.println("=== DEBUG SAVE START ===");
            System.out.println("Name: " + name);
            System.out.println("Artist: " + artist);
            System.out.println("Genre: " + genre);
            System.out.println("File: " + file.getOriginalFilename());

            // Tạo object Song thủ công
            Song song = new Song();
            song.setName(name);
            song.setArtist(artist);
            song.setGenre(genre);

            // Kiểm tra thư mục upload
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
                System.out.println("Created upload directory");
            }

            if (!file.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                File dest = new File(UPLOAD_DIR + fileName);

                System.out.println("Saving file to: " + dest.getAbsolutePath());
                file.transferTo(dest);

                song.setFilePath(fileName);
                System.out.println("File saved: " + fileName);
            } else {
                throw new IllegalArgumentException("File không được để trống");
            }

            System.out.println("Saving to database...");
            songService.save(song);
            System.out.println("=== DEBUG SAVE SUCCESS ===");

            return "redirect:/songs";

        } catch (Exception e) {
            System.err.println("=== DEBUG SAVE ERROR ===");
            e.printStackTrace();
            model.addAttribute("error", "Lỗi: " + e.getMessage());
            // Giữ lại giá trị đã nhập
            model.addAttribute("name", name);
            model.addAttribute("artist", artist);
            model.addAttribute("genre", genre);
            return "create";
        }
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        try {
            Song song = songService.findById(id);
            if (song == null) {
                return "redirect:/songs?error=not_found";
            }
            model.addAttribute("song", song);
            return "edit";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/songs?error=load_failed";
        }
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") Long id,
                         @RequestParam("name") String name,
                         @RequestParam("artist") String artist,
                         @RequestParam("genre") String genre,
                         @RequestParam(value = "file", required = false) MultipartFile file,
                         Model model) {
        try {
            Song song = songService.findById(id);
            if (song == null) {
                return "redirect:/songs?error=not_found";
            }

            song.setName(name);
            song.setArtist(artist);
            song.setGenre(genre);

            if (file != null && !file.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                File dest = new File(UPLOAD_DIR + fileName);
                dest.getParentFile().mkdirs();
                file.transferTo(dest);
                song.setFilePath(fileName);
            }

            songService.update(song);
            return "redirect:/songs";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Lỗi cập nhật: " + e.getMessage());
            return "edit";
        }
    }

    @GetMapping("/play/{id}")
    public String play(@PathVariable Long id,
                       @RequestParam(value = "autoplay", defaultValue = "false") boolean autoplay,
                       Model model) {
        try {
            System.out.println("=== DEBUG PLAY START ===");
            System.out.println("Autoplay requested: " + autoplay);

            Song song = songService.findById(id);
            if (song == null) {
                System.out.println("Song not found with id: " + id);
                return "redirect:/songs?error=not_found";
            }

            System.out.println("Song found: " + song.getName());
            System.out.println("File path: " + song.getFilePath());

            // KIỂM TRA FILE TỒN TẠI
            File audioFile = new File(UPLOAD_DIR + song.getFilePath());
            System.out.println("Audio file exists: " + audioFile.exists());

            String fileType = "audio/mpeg";
            if (song.getFilePath() != null) {
                String fp = song.getFilePath().toLowerCase();
                if (fp.endsWith(".flac")) fileType = "audio/flac";
                else if (fp.endsWith(".webm")) fileType = "audio/webm";
                else if (fp.endsWith(".wav")) fileType = "audio/wav";
                else if (fp.endsWith(".ogg")) fileType = "audio/ogg";
                else if (fp.endsWith(".mp3")) fileType = "audio/mpeg";
                else if (fp.endsWith(".m4a")) fileType = "audio/mp4";
            }

            // LẤY DANH SÁCH TẤT CẢ BÀI HÁT ĐỂ TẠO PLAYLIST
            List<Song> allSongs = songService.findAll();

            // TÌM BÀI HIỆN TẠI VÀ BÀI TIẾP THEO
            int currentIndex = -1;
            for (int i = 0; i < allSongs.size(); i++) {
                if (allSongs.get(i).getId().equals(id)) {
                    currentIndex = i;
                    break;
                }
            }

            Long nextSongId = null;
            if (currentIndex != -1 && currentIndex < allSongs.size() - 1) {
                nextSongId = allSongs.get(currentIndex + 1).getId();
            }

            System.out.println("Current index: " + currentIndex);
            System.out.println("Next song ID: " + nextSongId);
            System.out.println("Total songs: " + allSongs.size());
            System.out.println("=== DEBUG PLAY END ===");

            model.addAttribute("song", song);
            model.addAttribute("fileType", fileType);
            model.addAttribute("nextSongId", nextSongId);
            model.addAttribute("totalSongs", allSongs.size());
            model.addAttribute("currentIndex", currentIndex + 1);
            model.addAttribute("autoplay", autoplay); // 🆕 TRUYỀN AUTOPLAY VÀO VIEW

            return "view";
        } catch (Exception e) {
            System.err.println("=== DEBUG PLAY ERROR ===");
            e.printStackTrace();
            return "redirect:/songs?error=play_failed";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        try {
            // Lấy thông tin bài hát trước khi xóa để xóa file
            Song song = songService.findById(id);
            if (song != null && song.getFilePath() != null) {
                File audioFile = new File(UPLOAD_DIR + song.getFilePath());
                if (audioFile.exists()) {
                    audioFile.delete();
                    System.out.println("Deleted audio file: " + song.getFilePath());
                }
            }

            songService.delete(id);
            return "redirect:/songs";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/songs?error=delete_failed";
        }
    }

    // TRONG PHƯƠNG THỨC playNext
    @GetMapping("/next/{currentId}")
    public String playNext(@PathVariable Long currentId) {
        try {
            List<Song> allSongs = songService.findAll();
            int currentIndex = -1;

            for (int i = 0; i < allSongs.size(); i++) {
                if (allSongs.get(i).getId().equals(currentId)) {
                    currentIndex = i;
                    break;
                }
            }

            if (currentIndex != -1 && currentIndex < allSongs.size() - 1) {
                Long nextSongId = allSongs.get(currentIndex + 1).getId();
                // 🆕 THÊM PARAMETER AUTOPLAY
                return "redirect:/songs/play/" + nextSongId + "?autoplay=true";
            } else {
                // Nếu là bài cuối, quay lại bài đầu
                return "redirect:/songs/play/" + allSongs.get(0).getId() + "?autoplay=true";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/songs?error=next_failed";
        }
    }

    // TRONG PHƯƠNG THỨC playPrevious
    @GetMapping("/prev/{currentId}")
    public String playPrevious(@PathVariable Long currentId) {
        try {
            List<Song> allSongs = songService.findAll();
            int currentIndex = -1;

            for (int i = 0; i < allSongs.size(); i++) {
                if (allSongs.get(i).getId().equals(currentId)) {
                    currentIndex = i;
                    break;
                }
            }

            if (currentIndex > 0) {
                Long prevSongId = allSongs.get(currentIndex - 1).getId();
                // 🆕 THÊM PARAMETER AUTOPLAY
                return "redirect:/songs/play/" + prevSongId + "?autoplay=true";
            } else {
                // Nếu là bài đầu, chuyển đến bài cuối
                Long lastSongId = allSongs.get(allSongs.size() - 1).getId();
                return "redirect:/songs/play/" + lastSongId + "?autoplay=true";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/songs?error=prev_failed";
        }
    }

    // THÊM PHƯƠNG THỨC DỌN DẸP FILE CŨ
    @GetMapping("/cleanup")
    public String cleanupOldFiles() {
        try {
            File uploadDir = new File(UPLOAD_DIR);
            File[] files = uploadDir.listFiles();
            int deletedCount = 0;

            if (files != null) {
                // Lấy danh sách file đang được sử dụng trong database
                List<Song> allSongs = songService.findAll();

                for (File file : files) {
                    boolean isUsed = false;
                    for (Song song : allSongs) {
                        if (song.getFilePath() != null && file.getName().equals(song.getFilePath())) {
                            isUsed = true;
                            break;
                        }
                    }

                    // Xóa file không được sử dụng
                    if (!isUsed) {
                        if (file.delete()) {
                            deletedCount++;
                            System.out.println("Deleted unused file: " + file.getName());
                        }
                    }
                }
            }

            return "redirect:/songs?cleanup=" + deletedCount;
        } catch (Exception e) {
            return "redirect:/songs?cleanup_error=" + e.getMessage();
        }
    }
}