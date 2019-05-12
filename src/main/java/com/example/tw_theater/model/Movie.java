package com.example.tw_theater.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    private String id;
    private String title;
    @Column(name = "original_title")
    private String originalTitle;
    private String alt;
    private String image;
    private double rate;
    private int year;
    @ManyToMany
    @JoinTable(name = "cast", joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "film_maker_id"))
    private List<FilmMaker> casts;
    @ManyToMany
    @JoinTable(name = "director", joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "film_maker_id"))
    private List<FilmMaker> directors;
    @ManyToMany
    @JoinTable(name = "movie_genre", joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return this.originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getAlt() {
        return this.alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getRate() {
        return this.rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", alt='" + alt + '\'' +
                ", image='" + image + '\'' +
                ", rate=" + rate +
                ", year=" + year +
                '}';
    }

    public List<FilmMaker> getCasts() {
        return this.casts;
    }

    public void setCasts(List<FilmMaker> casts) {
        this.casts = casts;
    }

    public List<FilmMaker> getDirectors() {
        return this.directors;
    }

    public void setDirectors(List<FilmMaker> directors) {
        this.directors = directors;
    }

    public List<Genre> getGenres() {
        return this.genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
