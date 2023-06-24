package com.Online.Cinema.repository;

import com.Online.Cinema.entity.Film;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface FilmDao extends Repository<Film, Long> {

    Optional<Film> findAll();
    Film findById(Long id);
    Optional<Film> findByName(String name);
}
