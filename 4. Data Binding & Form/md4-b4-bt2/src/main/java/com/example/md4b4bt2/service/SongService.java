package com.example.md4b4bt2.service;


import com.example.md4b4bt2.model.Song;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongService {

    private final List<Song> songs = new ArrayList<>();

    public void save(Song song) {
        songs.add(song);
    }

    public List<Song> findAll() {
        return songs;
    }
}
