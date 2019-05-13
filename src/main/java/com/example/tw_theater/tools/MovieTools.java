package com.example.tw_theater.tools;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.tw_theater.dao.Response;
import com.example.tw_theater.model.FilmMaker;
import com.example.tw_theater.model.Genre;
import com.example.tw_theater.model.Movie;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MovieTools {
    private FilmMakerTools filmMakerTools = new FilmMakerTools();
    private GenreTools genreTools = new GenreTools();

    public List<Movie> getMovies(int start, int count) {
        List<Movie> movies = new ArrayList<>();
            String movie_url = "https://api.douban.com/v2/movie/top250?start=" + start + "&count=" + count;
            movies.addAll(generateMovies(movie_url));
        return movies;
    }

    List<Movie> generateMovies(String movie_url) {
        String response = Response.getData(movie_url);
        JSONObject date = JSONObject.parseObject(response, (Type) Object.class);
        JSONArray subjects = date.getJSONArray("subjects");

        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < subjects.size(); i++) {
            JSONObject subject = subjects.getJSONObject(i);
            Movie movie = generateMovie(subject);
            movies.add(movie);
        }
        return movies;
    }

    Movie generateMovie(JSONObject subject) {
        String id = subject.getString("id");
        String title = subject.getString("title");
        String originalTitle = subject.getString("original_title");
        String alt = subject.getString("alt");
        String image = subject.getJSONObject("images").getString("small");
        double rate = subject.getJSONObject("rating").getDouble("average");
        int year = subject.getInteger("year");
        JSONArray directorsStr = subject.getJSONArray("directors");
        JSONArray castsStr = subject.getJSONArray("casts");
        JSONArray genresStr = subject.getJSONArray("genres");

        List<FilmMaker> directors = filmMakerTools.generateFilmMakers(directorsStr);
        List<FilmMaker> casts = filmMakerTools.generateFilmMakers(castsStr);
        List<Genre> genres = genreTools.getGenres(genresStr);

        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setOriginalTitle(originalTitle);
        movie.setAlt(alt);
        movie.setImage(image);
        movie.setRate(rate);
        movie.setYear(year);
        movie.setDirectors(directors);
        movie.setCasts(casts);
        movie.setGenres(genres);
        return movie;
    }
}
