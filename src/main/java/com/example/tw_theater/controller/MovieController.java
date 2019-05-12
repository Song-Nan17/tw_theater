package com.example.tw_theater.controller;

import com.example.tw_theater.dao.FilmMakerRepository;
import com.example.tw_theater.dao.GenreRepository;
import com.example.tw_theater.dao.MovieRepository;
import com.example.tw_theater.model.FilmMaker;
import com.example.tw_theater.model.Genre;
import com.example.tw_theater.model.Movie;
import com.example.tw_theater.tools.MovieTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private FilmMakerRepository filmMakerRepository;
    @Autowired
    private GenreRepository genreRepository;

    private MovieTools movieTools = new MovieTools();

    @PostMapping("/movies")
    Movie storageMovies() {
        List<Movie> movies = movieTools.getMovies();
        Movie movie = movies.get(0);
        storageFilmMaker(movie);
        storageGenres(movie);
        movieRepository.save(movie);
        return movie;
    }

    void storageFilmMaker(Movie movie) {
        HashSet<FilmMaker> filmMakers = new HashSet<>();
        filmMakers.addAll(movie.getCasts());
        filmMakers.addAll(movie.getDirectors());
        filmMakerRepository.saveAll(filmMakers);
    }

    void storageGenres(Movie movie) {
        List<Genre> genres = new ArrayList<>();
        movie.getGenres().forEach(genre -> {
            Genre unrepeatedGenre = genreRepository.findByNameEquals(genre.getName()).orElse(genre);
            genres.add(unrepeatedGenre);
        });
        genreRepository.saveAll(genres);
    }
}
