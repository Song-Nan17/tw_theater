package com.example.tw_theater.controller;

import com.example.tw_theater.dao.GenreRepository;
import com.example.tw_theater.dao.MovieRepository;
import com.example.tw_theater.model.Genre;
import com.example.tw_theater.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private GenreRepository genreRepository;

    @GetMapping("/movies")
    Iterable<Movie> findByGenresContain(@RequestParam("genre") String genreName,
                               @RequestParam("page") int page,
                               @RequestParam("size") int size){
        Genre genre = genreRepository.findByName(genreName).get();
        return movieRepository.findByGenresContains(genre, PageRequest.of(page, size));
    }
}
