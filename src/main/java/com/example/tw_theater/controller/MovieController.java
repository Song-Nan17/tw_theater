package com.example.tw_theater.controller;

import com.example.tw_theater.dao.MovieRepository;
import com.example.tw_theater.model.Movie;
import com.example.tw_theater.tools.MovieTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;
    private FilmMakerController filmMakerController = new FilmMakerController();
    private MovieTools movieTools = new MovieTools();

    @PostMapping("/movies")
    Movie storageMovies() {
        List<Movie> movies = movieTools.getMovies();
        Movie movie = movies.get(0);
        filmMakerController.storageFilmMaker(movie);
        movieRepository.save(movie);
        return movie;
    }


    @GetMapping("/movies")
    Iterable<Movie> getMovies() {
        return movieRepository.findAll();
    }
}
