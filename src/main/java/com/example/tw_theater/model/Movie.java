package com.example.tw_theater.model;

import javax.persistence.*;
import java.time.Year;

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
}
