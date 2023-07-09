package com.Online.Cinema.service;

import com.Online.Cinema.entity.Image;
import com.Online.Cinema.repository.ImageDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageDao imageDao;

    public List<Image> findByFilmId(Long filmId){
        return imageDao.findByFilmId(filmId);
    }

    public Optional<Image> findById(Long id){
        return imageDao.findById(id);
    }

    public void save(Image image){
        imageDao.save(image);
    }
}
