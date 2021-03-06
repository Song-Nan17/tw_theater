package com.example.tw_theater.controller;


import com.alibaba.fastjson.JSON;
import com.example.tw_theater.dao.CommentRepository;
import com.example.tw_theater.model.Comment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/movies/{id}/comments")
    String getCommentsByMovie(@PathVariable String id, HttpServletRequest request) {
        List<Comment> comments = commentRepository.findByMovieId(id);
        String callback = request.getParameter("callback");
        return callback + "(" + JSON.toJSONString(comments) + ")";
    }
}
