package com.Online.Cinema.controller;

import com.Online.Cinema.entity.Film;
import com.Online.Cinema.exeption.FilmNotFounded;
import com.Online.Cinema.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @RequestMapping(value="/films", method = RequestMethod.GET)
    public List<Film> getAllFilm() throws FilmNotFounded {

        Film film = new Film();

        film.setId(1);
        film.setPath("");
        film.setName("Hello");
        film.setDescription("sd");

        filmService.createNewFilm(film);

        return filmService.findAll();
    }
}
