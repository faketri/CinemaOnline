package com.Online.Cinema.service;

import com.Online.Cinema.entity.Video;
import com.Online.Cinema.repository.VideoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {
    @Autowired
    private VideoDao videoDao;

    public Optional<Video> findById(Long id) throws ChangeSetPersister.NotFoundException {
        return Optional.ofNullable(videoDao.findById(id).orElseThrow(
                ChangeSetPersister.NotFoundException::new));
    }

    public List<Video> findByFilmId(Long filmId){
        return videoDao.findByFilmId(filmId);
    }

    public void save(Video video){
        videoDao.save(video);
    }
}
