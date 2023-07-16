package com.Online.Cinema.controller;

import com.Online.Cinema.exeption.FilmNotFounded;
import com.Online.Cinema.service.FilmService;
import com.Online.Cinema.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class FilmController {
    @Autowired
    private FilmService filmService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getAllFilm(Model model, Principal principal){
        if(principal != null){
            model.addAttribute("user",
                            "/user/profile/" +
                            userService.findByLogin(principal.getName())
                                    .orElseThrow(() ->
                                            new UsernameNotFoundException(principal.getName())
                                    ).getId()
            );
        }else{
            model.addAttribute("user", "/auth/login/");
        }

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
}
