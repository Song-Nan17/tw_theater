package com.example.tw_theater.controller;

import com.example.tw_theater.dao.FilmMakerRepository;
import com.example.tw_theater.model.FilmMaker;
import com.example.tw_theater.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
public class FilmMakerController {
    @Autowired
    private FilmMakerRepository filmMakerRepository;

    void storageFilmMaker(Movie movie) {
        HashSet<FilmMaker> filmMakers = new HashSet<>();
        filmMakers.addAll(movie.getCasts());
        filmMakers.addAll(movie.getDirectors());
        filmMakerRepository.saveAll(filmMakers);
    }
}
