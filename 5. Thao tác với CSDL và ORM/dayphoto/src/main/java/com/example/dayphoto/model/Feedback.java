package com.example.dayphoto.model;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "feedbacks")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String author;
    private String comment;
    private int rating; // từ 1-5
    private int likes = 0;

    private LocalDate date; // ngày gửi feedback

    public Feedback() {
    }

    public Feedback(String author, String comment, int rating, LocalDate date) {
        this.author = author;
        this.comment = comment;
        this.rating = rating;
        this.date = date;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public int getLikes() { return likes; }
    public void setLikes(int likes) { this.likes = likes; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
