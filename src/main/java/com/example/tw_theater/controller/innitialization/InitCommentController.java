package com.example.tw_theater.controller.innitialization;


import com.example.tw_theater.dao.CommentRepository;
import com.example.tw_theater.dao.MovieRepository;
import com.example.tw_theater.model.Movie;
import com.example.tw_theater.tools.CommentTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitCommentController {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MovieRepository movieRepository;

    private CommentTools commentTools = new CommentTools();

    @PostMapping("/comments")
    void storageComments() {
        Iterable<Movie> movies = movieRepository.findAll();
        movies.forEach(movie -> commentRepository.saveAll(commentTools.getCommentsByMovie(movie.getId(),10)));
    }
}
