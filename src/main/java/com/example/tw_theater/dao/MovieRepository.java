package com.example.tw_theater.dao;

import com.example.tw_theater.model.Genre;
import com.example.tw_theater.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, String> {

    Page<Movie> findByOriginalTitleLikeOrTitleLikeAndGenresContains(
            String title, String originalTitle, Genre genre, Pageable pageable);

    Page<Movie> findAll(Pageable pageable);

    Page<Movie> findByInTheaterIsTrue(Pageable pageable);
}
