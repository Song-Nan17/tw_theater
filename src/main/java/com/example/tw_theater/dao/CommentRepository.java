package com.example.tw_theater.dao;

import com.example.tw_theater.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, String> {
    List<Comment> findByMovieId(String movieId);
}
