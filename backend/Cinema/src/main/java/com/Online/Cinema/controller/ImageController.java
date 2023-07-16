package com.Online.Cinema.controller;

import com.Online.Cinema.entity.Image;
import com.Online.Cinema.service.ImageService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;

@Controller
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/image/{idFilm}/{imageType}")
    private void getImageForFilm(
            HttpServletResponse response,
            @PathVariable(value = "idFilm") Long filmId,
            @PathVariable(value = "imageType") int imageType) throws IOException {

        Image image = imageService.findByFilmId(filmId).get(imageType);

        response.setContentType("image/jpeg");
        response.getOutputStream().write(image.getPhoto());
        response.getOutputStream().close();
    }

    @GetMapping("/image/{idImage}")
    private void getImage(
            HttpServletResponse response,
            @PathVariable(value = "idImage") Long idImage) throws IOException {

        Image image = imageService
                .findById(idImage)
                .orElseThrow(() ->
                    new FileSystemNotFoundException(
                            String.format("Image by id - %d not found", idImage)
                    )
                );

        response.setContentType("image/jpeg");
        response.getOutputStream().write(image.getPhoto());
        response.getOutputStream().close();
    }
}
