package com.example.md4b5bt1.service;

import com.example.md4b5bt1.model.Song;
import java.util.List;

public interface ISongService {
    List<Song> findAll();
    Song findById(Long id);
    void save(Song song);
    void update(Song song);
    void delete(Long id);
}
