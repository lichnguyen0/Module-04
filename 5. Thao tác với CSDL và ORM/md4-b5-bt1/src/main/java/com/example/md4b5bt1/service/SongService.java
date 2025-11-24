package com.example.md4b5bt1.service;

import com.example.md4b5bt1.model.Song;
import com.example.md4b5bt1.repository.ISongRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SongService implements ISongService {

    @Autowired
    private ISongRepository repository;

    public List<Song> findAll() { return repository.findAll(); }
    public Song findById(Long id) { return repository.findById(id); }
    public void save(Song song) { repository.save(song); }
    public void update(Song song) { repository.update(song); }
    public void delete(Long id) { repository.delete(id); }
}
