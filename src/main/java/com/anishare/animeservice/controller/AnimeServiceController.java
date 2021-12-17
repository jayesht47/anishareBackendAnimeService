package com.anishare.animeservice.controller;

import com.anishare.animeservice.models.Anime;
import com.anishare.animeservice.service.AnimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/anime")
@Slf4j
@CrossOrigin
public class AnimeServiceController {

    @Autowired
    AnimeService animeService;

    @GetMapping("/{animeId}")
    public Anime getAnimeById(@PathVariable("animeId") Long animeId)
    {
        return animeService.getAnimeById(animeId);
    }

}
