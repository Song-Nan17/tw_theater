package com.example.tw_theater.controller.innitialization;

import com.example.tw_theater.dao.GenreRepository;
import com.example.tw_theater.model.Genre;
import com.example.tw_theater.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InitGenreController {
    @Autowired
    private static GenreRepository genreRepository;

    public static void saveGenres(Movie movie) {
        List<Genre> genres = setGenreIdWhenInDB(movie.getGenres());
        movie.setGenres(genres);
        genreRepository.saveAll(movie.getGenres());
    }

    static List<Genre> setGenreIdWhenInDB(List<Genre> genres) {
        List<Genre> result = new ArrayList<>();
        for (int i = 0; i < genres.size(); i++) {
            String name = genres.get(i).getName();
            Genre genre = genreRepository.findByName(name).orElse(genres.get(i));
            result.add(genre);
        }
        return result;
    }
}

