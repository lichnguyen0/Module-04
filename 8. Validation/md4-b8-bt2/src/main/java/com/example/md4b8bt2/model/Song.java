package com.example.md4b8bt2.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên bài hát không được để trống")
    @Size(max = 800, message = "Tên bài hát không quá 800 ký tự")
    @Pattern(regexp = "^[^@;=\\-+,.!]*$", message = "Tên bài hát không được chứa ký tự đặc biệt")
    private String title;

    @NotBlank(message = "Tên nghệ sĩ không được để trống")
    @Size(max = 300, message = "Tên nghệ sĩ không quá 300 ký tự")
    @Pattern(regexp = "^[^@;=\\-+,.!]*$", message = "Tên nghệ sĩ không được chứa ký tự đặc biệt")
    private String artist;

    @NotBlank(message = "Thể loại nhạc không được để trống")
    @Size(max = 1000, message = "Thể loại nhạc không quá 1000 ký tự")
    @Pattern(regexp = "^[^@;=\\-+.!]*$", message = "Thể loại chỉ được phép dấu phẩy")
    private String genre;

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
}
