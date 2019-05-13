package com.example.tw_theater.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    private String id;
    @Column(name = "movie_id")
    private String movieId;
    private String author;
    private Integer rate;
    private String content;
    @Column(name = "create_at")
    private String time;

    public Comment(){

    }

    public Comment(String id, String movieId, String author, Integer rate, String content, String time) {
        this.id = id;
        this.movieId = movieId;
        this.author = author;
        this.rate = rate;
        this.content = content;
        this.time = time;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieId() {
        return this.movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getRate() {
        return this.rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
