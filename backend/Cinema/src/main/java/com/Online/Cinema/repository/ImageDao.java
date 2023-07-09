package com.Online.Cinema.repository;

import com.Online.Cinema.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageDao extends JpaRepository<Image, Long> {

    List<Image> findByFilmId(Long filmId);
    Optional<Image> findById(Long id);
}
