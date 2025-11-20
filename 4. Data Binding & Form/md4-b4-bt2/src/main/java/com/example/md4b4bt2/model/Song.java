package com.example.md4b4bt2.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class Song {
    private String title;
    private String artist;
    private List<String> genres; // List<String> thực sự lưu thể loại
    private MultipartFile file;  // file upload
    private String genresStr;    // field dùng cho form binding

    // Getter & Setter
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    public List<String> getGenres() { return genres; }
    public void setGenres(List<String> genres) { this.genres = genres; }  // ✅ Thêm setter

    public MultipartFile getFile() { return file; }
    public void setFile(MultipartFile file) { this.file = file; }

    public String getGenresStr() { return genresStr; }
    public void setGenresStr(String genresStr) { this.genresStr = genresStr; }
}
