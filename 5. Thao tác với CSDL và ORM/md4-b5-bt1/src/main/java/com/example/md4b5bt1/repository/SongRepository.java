package com.example.md4b5bt1.repository;

import com.example.md4b5bt1.model.Song;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SongRepository implements ISongRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Song> findAll() {
        return getCurrentSession().createQuery("FROM Song", Song.class).getResultList();
    }

    @Override
    public Song findById(Long id) {
        return getCurrentSession().get(Song.class, id);
    }

    @Override
    public void save(Song song) {
        getCurrentSession().save(song);
    }

    @Override
    public void update(Song song) {
        getCurrentSession().update(song);
    }

    @Override
    public void delete(Long id) {
        Song song = findById(id);
        if (song != null) getCurrentSession().delete(song);
    }
}