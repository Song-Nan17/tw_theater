package com.example.tw_theater.controller.innitialization;

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
            List<Movie> movies = movieTools.getMovies(start, count);
            movies.forEach(movie -> saveMovie(movie));
        }
    }

    void saveMovie(Movie movie) {
        List<Genre> genres = setGenreIdWhenInDB(movie.getGenres());
        List<FilmMaker> casts = setFilmMakerIdWhenInDB(movie.getCasts());
        List<FilmMaker> directors = setFilmMakerIdWhenInDB(movie.getCasts());
        movie.setGenres(genres);
        movie.setCasts(casts);
        movie.setDirectors(directors);
        filmMakerRepository.saveAll(movie.getCasts());
        filmMakerRepository.saveAll(movie.getDirectors());
        genreRepository.saveAll(movie.getGenres());
        movieRepository.save(movie);
    }


    List<FilmMaker> setFilmMakerIdWhenInDB(List<FilmMaker> filmMakers) {
        List<FilmMaker> result = new ArrayList<>();
        for (int i = 0; i < filmMakers.size(); i++) {
            String name = filmMakers.get(i).getName();
            String userId = filmMakers.get(i).getUserId();
            FilmMaker filmMaker = filmMakerRepository
                    .findByNameAndUserId(name, userId)
                    .orElse(filmMakers.get(i));
            result.add(filmMaker);
        }
        return result;
    }

    List<Genre> setGenreIdWhenInDB(List<Genre> genres) {
        List<Genre> result = new ArrayList<>();
        for (int i = 0; i < genres.size(); i++) {
            String name = genres.get(i).getName();
            Genre genre = genreRepository.findByName(name).orElse(genres.get(i));
            result.add(genre);
        }
        return result;
    }
}
