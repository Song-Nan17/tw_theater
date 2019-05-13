package com.example.tw_theater.controller;

import com.example.tw_theater.dao.GenreRepository;
import com.example.tw_theater.dao.MovieRepository;
import com.example.tw_theater.model.Genre;
import com.example.tw_theater.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private GenreRepository genreRepository;

    private Integer page = 0;
    private Integer size = 10;
    private PageRequest pageRequest = null;

    void setPageRequest(Integer page, Integer size) {
        this.page = page == null ? 0 : page;
        this.size = size == null ? 10 : size;
        this.pageRequest = PageRequest.of(this.page, this.size);
    }

    @GetMapping("/movies")
    Page<Movie> findMovies(String title, String genre, Integer page, Integer size) {
        setPageRequest(page, size);
        Page<Movie> movies = null;
        if (title != null && genre != null) {
            movies = findByTitleLikeAndGenresContain(title, genre);
        } else if (genre != null) {
            movies = findByGenresContain(genre);
        } else if (title != null) {
            movies = findByTitleLike(title);
        } else {
            movies = movieRepository.findAll(this.pageRequest);
        }
        return movies;
    }

    Page<Movie> findByTitleLike(String title) {
        String titleLike = "%" + title + "%";
        return movieRepository
                .findByOriginalTitleLikeOrTitleLike(titleLike, titleLike, this.pageRequest);
    }

    Page<Movie> findByGenresContain(String genreName) {
        Genre genre = genreRepository.findByName(genreName).get();
        return movieRepository.findByGenresContains(genre, this.pageRequest);
    }

    Page<Movie> findByTitleLikeAndGenresContain(String title, String genreName) {
        String titleLike = "%" + title + "%";
        Genre genre = genreRepository.findByName(genreName).get();
        return movieRepository
                .findByOriginalTitleLikeOrTitleLikeAndGenresContains(
                        titleLike, titleLike, genre, this.pageRequest);
    }

    @GetMapping("/movies/in_theater")
    Page<Movie> findMoviesIsInTheater(Integer page, Integer size) {
        setPageRequest(page, size);
        return movieRepository.findByInTheaterIsTrue(this.pageRequest);
    }

}
