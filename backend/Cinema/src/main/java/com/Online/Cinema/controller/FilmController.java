package com.Online.Cinema.controller;

import com.Online.Cinema.entity.Film;
import com.Online.Cinema.entity.Image;
import com.Online.Cinema.entity.Video;
import com.Online.Cinema.exeption.FilmNotFounded;
import com.Online.Cinema.service.FilmService;
import com.Online.Cinema.service.ImageService;
import com.Online.Cinema.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
@RequiredArgsConstructor
public class FilmController {
    @Autowired
    private FilmService filmService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private VideoService videoService;

    @GetMapping("/")
    public String getAllFilm(Model model) throws IOException {

        model.addAttribute("films", filmService.findAll());
        return "index";
    }

    @GetMapping(value = "/film/{id}")
    public String getFilm(@PathVariable(value = "id") Long id, Model model) throws FilmNotFounded {

        model.addAttribute("film", filmService.findById(id).orElseThrow(() ->
                new FilmNotFounded( String.format("Film not found %d", id) ))
        );

        return "filmPage";
    }
/*
    @GetMapping("/createFilm")
    public void create() throws IOException {

        Film film = new Film();

        film.setName("JOHN BIBER");
        film.setSmallDescription("THE BEST FILM OF THE RUSSIA");
        film.setDescription("FAKETPLAIT IS THE BEST OF THE LONG FILM AUDITION MAGER COUNTRY ARITHMETHIC MYSKEL MISTER");

        filmService.save(film);

        Image image = new Image();
        image.setPhoto(Files.readAllBytes(
                Path.of("D:\\Project\\Java\\VideoService\\backend\\Cinema\\src\\main\\resources\\static\\assets\\How-long-does-it-take-to-make-a-movie-trailer-explained.jpg")
        ));

        image.setFilm(film);

        Image image1 = new Image();

        image1.setPhoto(Files.readAllBytes(
                Path.of("D:\\Project\\Java\\VideoService\\backend\\Cinema\\src\\main\\resources\\static\\assets\\photo-1508919801845-fc2ae1bc2a28.jpg")
        ));

        image1.setFilm(film);

        imageService.save(image);
        imageService.save(image1);

        film.setImage(imageService.findByFilmId(film.getId()));

        var video = new Video();
        video.setPath("\\video\\Forza Horizon 5 2023.06.27 - 18.31.12.02.DVR.mp4");
        video.setFilm(film);

        videoService.save(video);

        film.setVideos(videoService.findByFilmId(film.getId()));

    }*/
}
