package com.Online.Cinema.repository;

import com.Online.Cinema.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmDao extends JpaRepository<Film, Long> {

    List<Film> findAll();
    Optional<Film> findById(Long id);
    Optional<Film> findByName(String name);
}
