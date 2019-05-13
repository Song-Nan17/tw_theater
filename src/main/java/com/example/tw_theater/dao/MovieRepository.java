package com.example.tw_theater.dao;

import com.example.tw_theater.model.Genre;
import com.example.tw_theater.model.Movie;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, String> {
    List<Movie> findByGenresContains(Genre genre, Pageable pageable);

    List<Movie> findByTitle(String title);
}
