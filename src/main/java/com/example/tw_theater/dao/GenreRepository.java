package com.example.tw_theater.dao;

import com.example.tw_theater.model.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre,Iterable> {
    Optional<Genre> findByName(String name);
}
