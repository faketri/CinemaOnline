package com.Online.Cinema.repository;

import com.Online.Cinema.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoDao extends JpaRepository<Video, Long> {

    Optional<Video> findById(Long id);
    List<Video> findByFilmId(Long filmId);

}
