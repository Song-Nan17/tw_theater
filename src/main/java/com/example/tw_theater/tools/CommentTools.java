package com.example.tw_theater.tools;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.tw_theater.dao.Response;
import com.example.tw_theater.model.Comment;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CommentTools {
    public List<Comment> getCommentsByMovie(String movieId, int count) {
        List<Comment> comments = new ArrayList<>();
        String comment_url = "https://api.douban.com/v2/movie/subject/" + movieId +
                "/comments?apikey=0b2bdeda43b5688921839c8ecb20399b&count=" + count + "&client=&udid=";
        comments.addAll(generateComments(comment_url,movieId));
        return comments;
    }

    private List<Comment> generateComments(String comment_url,String movieId) {
        String response = Response.getData(comment_url);
        JSONObject date = JSONObject.parseObject(response, (Type) Object.class);
        JSONArray commentsJson = date.getJSONArray("comments");

        List<Comment> comments = new ArrayList<>();
        for (int i = 0; i < commentsJson.size(); i++) {
            JSONObject commentObject = commentsJson.getJSONObject(i);
            Comment comment = generateComment(commentObject,movieId);
            comments.add(comment);
        }
        return comments;
    }

    private Comment generateComment(JSONObject commentObject,String movieId) {
        String id = commentObject.getString("id");
        String author = commentObject.getJSONObject("author").getString("name");
        Integer rate = commentObject.getJSONObject("rating").getInteger("value");
        String content = commentObject.getString("content");
        Timestamp time = commentObject.getTimestamp("created_at");

        Comment comment = new Comment(id,movieId,author,rate,content,time);
        return comment;
    }
}
