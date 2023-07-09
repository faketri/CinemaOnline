package com.Online.Cinema.service;

import com.Online.Cinema.entity.Film;
import com.Online.Cinema.repository.FilmDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilmService {
    private final FilmDao filmDao;

    public List<Film> findAll(){
        return filmDao.findAll();
    }

    public Optional<Film> findById(Long id){
        return filmDao.findById(id);
    }

    public Optional<Film> findByName(String name){
        return filmDao.findByName(name);
    }

    public void save(Film film){
        filmDao.save(film);
    }


//    public List<Film> findTopFilmsForUserRating(){
//        return filmDao.findTopFilmsForUserRating();
//    }
}
