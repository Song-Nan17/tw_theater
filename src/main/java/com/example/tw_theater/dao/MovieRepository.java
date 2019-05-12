package com.example.tw_theater.dao;

import com.example.tw_theater.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, String> {
}
