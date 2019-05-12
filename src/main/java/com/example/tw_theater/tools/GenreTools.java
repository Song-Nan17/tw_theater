package com.example.tw_theater.tools;

import com.alibaba.fastjson.JSONArray;
import com.example.tw_theater.model.Genre;

import java.util.ArrayList;
import java.util.List;

public class GenreTools {
    public List<Genre> getGenres(JSONArray genresStr) {
        List<Genre> genres = new ArrayList<>();
        for (int i = 0; i < genresStr.size(); i++) {
            String name = genresStr.getString(i);
            Genre genre = new Genre();
            genre.setName(name);
            genres.add(genre);
        }
        return genres;
    }
}
