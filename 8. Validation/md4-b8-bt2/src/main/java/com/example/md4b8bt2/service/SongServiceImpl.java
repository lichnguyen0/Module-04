package com.example.md4b8bt2.service;


import com.example.md4b8bt2.model.Song;
import com.example.md4b8bt2.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements ISongService {

    @Autowired
    private SongRepository songRepository;

    @Override
    public void save(Song song) {
        songRepository.save(song);
    }

    @Override
    public List<Song> findAll() {
        return songRepository.findAll();
    }
}
