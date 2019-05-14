package com.example.tw_theater.controller.innitialization;

import com.example.tw_theater.dao.FilmMakerRepository;
import com.example.tw_theater.dao.GenreRepository;
import com.example.tw_theater.dao.MovieRepository;
import com.example.tw_theater.model.Movie;
import com.example.tw_theater.tools.MovieTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InitMovieController {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private FilmMakerRepository filmMakerRepository;
    @Autowired
    private GenreRepository genreRepository;

    private MovieTools movieTools = new MovieTools();

    @PostMapping("/movies")
    void storageMovies() {
        int start = 0;
        int count = 50;
        for (int i = 0; i < 5; i++, start += count) {
            String url = "https://api.douban.com/v2/movie/top250?apikey=0df993c66c0c636e29ecbb5344252a4a&start="
                    + start + "&count=" + count;
            List<Movie> movies = movieTools.getMovies(url);
            movies.forEach(movie -> saveMovie(movie));
        }
    }

    @PostMapping("/movies/in_theater")
    List<Movie> storageMoviesIsInTheater() {
        String url = "https://api.douban.com/v2/movie/in_theaters?apikey=0df993c66c0c636e29ecbb5344252a4a";
        List<Movie> movies = movieTools.getMovies(url);
        movies.forEach(movie -> {
            movie.setInTheater(true);
            saveMovie(movie);
        });
        return movies;
    }

    void saveMovie(Movie movie) {
        InitFilmMakerController.saveCasts(movie);
        InitFilmMakerController.saveDirectors(movie);
        InitGenreController.saveGenres(movie);
        movieRepository.save(movie);
    }

    @PostMapping("/movies/introduction")
    void storageSumaryOfMovies() {
        Iterable<Movie> movies = movieRepository.findAll();
        movies.forEach(movie -> {
            String introduction = movieTools.getMovieIntroduction(movie.getId());
            movie.setIntroduction(introduction);
            movieRepository.save(movie);
        });
    }
}
