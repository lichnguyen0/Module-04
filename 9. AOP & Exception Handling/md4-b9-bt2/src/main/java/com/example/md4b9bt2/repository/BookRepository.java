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
        books.put(1L, new Book(1L, "Java Core", "Nguyen Van A", 3));
        books.put(2L, new Book(2L, "Spring in Action", "Craig Walls", 2));
        books.put(3L, new Book(3L, "Clean Code", "Robert C. Martin", 1));
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

