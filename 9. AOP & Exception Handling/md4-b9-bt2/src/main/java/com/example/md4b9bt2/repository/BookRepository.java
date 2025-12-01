package com.example.md4b9bt2.repository;



import com.example.md4b9bt2.model.Book;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BookRepository {
    private final Map<Long, Book> books = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        books.put(1L, new Book(1L, "Java Core", "Lâm Quỳnh Phương", 5));
        books.put(2L, new Book(2L, "Spring in Action", "Craig Walls", 2));
        books.put(3L, new Book(3L, "Clean Code", "Robert C. Martin", 1));
        books.put(4L, new Book(4L, "Basic Java", "Lưu Thị Tố Nhuận", 3));
        books.put(5L, new Book(5L, "Introduction to Java Programming của Daniel Liang", "Thạch Thị Gấm Xinh", 5));

    }

    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(books.get(id));
    }

    public void save(Book book) {
        books.put(book.getId(), book);
    }
}

