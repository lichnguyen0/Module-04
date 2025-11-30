package com.example.md4b8bt2.service;


import com.example.md4b8bt2.model.Song;

import java.util.List;

public interface ISongService {
    void save(Song song);
    List<Song> findAll();
}

