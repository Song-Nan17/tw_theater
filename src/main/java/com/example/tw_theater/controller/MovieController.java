package com.example.tw_theater.controller;

import com.example.tw_theater.dao.GenreRepository;
import com.example.tw_theater.dao.MovieRepository;
import com.example.tw_theater.model.Genre;
import com.example.tw_theater.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
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
    List<Movie> findMovies(String title, String genre, Integer page, Integer size) {
        List<Movie> movies = new ArrayList<>();
        if (title != null) {
            movies = findByTitle(title);
        } else if (genre != null) {
            movies = findByGenresContain(genre, page, size);
        }
        return movies;
    }

    List<Movie> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    List<Movie> findByGenresContain(String genreName, Integer page, Integer size) {
        Genre genre = genreRepository.findByName(genreName).get();
        page = page == null ? 0 : page;
        size = size == null ? 10 : size;
        return movieRepository.findByGenresContains(genre, PageRequest.of(page, size));
    }

}
