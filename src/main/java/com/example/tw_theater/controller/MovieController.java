package com.example.tw_theater.controller;

import com.example.tw_theater.dao.GenreRepository;
import com.example.tw_theater.dao.MovieRepository;
import com.example.tw_theater.model.Genre;
import com.example.tw_theater.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
        title = title == null ? "" : title;
        String titleLike = "%" + title + "%";
        Page<Movie> movies = null;
        if(genre==null) {
            movies = movieRepository.findAll(this.pageRequest);
        }else {
            movies = findByTitleLikeAndGenresContain(titleLike, genre);
        }
        return movies;
    }

    Page<Movie> findByTitleLikeAndGenresContain(String titleLike, String genreName) {
        Genre genre = genreRepository.findByName(genreName).get();
        return movieRepository
                .findByOriginalTitleLikeOrTitleLikeAndGenresContains(
                        titleLike, titleLike, genre, this.pageRequest);
    }

    @GetMapping("/movies/{id}")
    Optional<Movie> getById(@PathVariable("id") String id) {
        return movieRepository.findById(id);
    }

    @GetMapping("/movies/in_theater")
    Page<Movie> findMoviesIsInTheater(Integer page, Integer size) {
        setPageRequest(page, size);
        return movieRepository.findByInTheaterIsTrue(this.pageRequest);
    }
}
