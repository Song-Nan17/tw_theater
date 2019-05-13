package com.example.tw_theater.dao;

import com.example.tw_theater.model.FilmMaker;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface FilmMakerRepository extends CrudRepository<FilmMaker, String> {
    Optional<FilmMaker> findByNameAndUserId(String name, String userId);
}
