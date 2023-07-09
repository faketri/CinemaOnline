package com.Online.Cinema.controller;

import com.Online.Cinema.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class VideoController {
    private final VideoService videoService;

    @GetMapping("/video/{idFilm}/{idVideo}")
    public String getVideo(
             Model model,
             @PathVariable Long idFilm,
             @PathVariable Long idVideo){

        return videoService
                .findByFilmId(idFilm)
                .get(
                        Math.toIntExact(idVideo)
                ).getPath();
    }
}
