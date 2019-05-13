package com.example.tw_theater.controller;


import com.example.tw_theater.dao.CommentRepository;
import com.example.tw_theater.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/movies/{id}/comments")
    List<Comment> getCommentsByMovie(@PathVariable String id){
        return commentRepository.findByMovieId(id);
    }
}
