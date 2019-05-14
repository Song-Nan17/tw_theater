package com.example.tw_theater.controller.innitialization;

import com.example.tw_theater.dao.FilmMakerRepository;
import com.example.tw_theater.model.FilmMaker;
import com.example.tw_theater.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InitFilmMakerController {
    @Autowired
    private static FilmMakerRepository filmMakerRepository;

    static void saveCasts(Movie movie) {
        List<FilmMaker> casts = setFilmMakerIdWhenInDB(movie.getCasts());
        movie.setCasts(casts);
        filmMakerRepository.saveAll(movie.getCasts());
    }

    static void saveDirectors(Movie movie) {
        List<FilmMaker> directors = setFilmMakerIdWhenInDB(movie.getDirectors());
        movie.setDirectors(directors);
        filmMakerRepository.saveAll(movie.getDirectors());
    }

    static List<FilmMaker> setFilmMakerIdWhenInDB(List<FilmMaker> filmMakers) {
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
}
