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
        page = page == null ? 0 : page;
        size = size == null ? 10 : size;

        List<Movie> movies = new ArrayList<>();
        if (title != null && genre != null) {
            movies = findByTitleLikeAndGenresContain(title, genre, page, size);
        } else if (genre != null) {
            movies = findByGenresContain(genre, page, size);
        } else if (title != null) {
            movies = findByTitleLike(title, page, size);

        }
        return movies;
    }

    List<Movie> findByTitleLike(String title, Integer page, Integer size) {
        String titleLike = "%" + title + "%";
        return movieRepository.findByTitleLike(titleLike, PageRequest.of(page, size));
    }

    List<Movie> findByGenresContain(String genreName, Integer page, Integer size) {
        Genre genre = genreRepository.findByName(genreName).get();
        return movieRepository.findByGenresContains(genre, PageRequest.of(page, size));
    }

    List<Movie> findByTitleLikeAndGenresContain(
            String title, String genreName,
            Integer page, Integer size) {
        String titleLike = "%" + title + "%";
        Genre genre = genreRepository.findByName(genreName).get();
        return movieRepository
                .findByTitleLikeAndGenresContains(titleLike, genre, PageRequest.of(page, size));
    }

}
