package com.example.tw_theater.controller;

import com.example.tw_theater.dao.GenreRepository;
import com.example.tw_theater.dao.MovieRepository;
import com.example.tw_theater.model.Genre;
import com.example.tw_theater.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/movies")
    List<Movie> findByGenresContain(String title, String genreName, Integer page, Integer size) {
        List<Movie> movies = new ArrayList<>();
        if (title != null) {
            movies = movieRepository.findByTitle(title);
        }
//        Genre genre = genreRepository.findByName(genreName).get();
        return movies;
    }

}
