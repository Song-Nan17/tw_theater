package com.example.tw_theater.controller;

import com.alibaba.fastjson.JSON;
import com.example.tw_theater.dao.GenreRepository;
import com.example.tw_theater.dao.MovieRepository;
import com.example.tw_theater.model.Genre;
import com.example.tw_theater.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;

@RestController
public class GenreController {
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/genres")
    String getAllGenres(HttpServletRequest request) {
        Iterable<Genre> genres = genreRepository.findAll();
        String callback = request.getParameter("callback");
        return callback + "(" + JSON.toJSONString(genres) + ")";
    }

    @GetMapping("/movies/genres")
    String getGenresByMovieTitleLike(String title, HttpServletRequest request) {
        HashSet<Genre> genres = new HashSet<>();
        String titleLike = "%" + title + "%";
        List<Movie> movies = movieRepository
                .findByTitleLikeOrOriginalTitleLike(titleLike, titleLike);
        movies.forEach(movie -> genres.addAll(movie.getGenres()));
        String callback = request.getParameter("callback");
        return callback + "(" + JSON.toJSONString(genres) + ")";

    }
}
