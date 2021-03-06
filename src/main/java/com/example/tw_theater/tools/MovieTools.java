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

    public List<Movie> getMovies(String url) {
        List<Movie> movies = new ArrayList<>();
        movies.addAll(generateMovies(url));
        return movies;
    }

    List<Movie> generateMovies(String url) {
        String response = Response.getData(url);
        JSONObject data = JSONObject.parseObject(response, (Type) Object.class);
        JSONArray subjects = data.getJSONArray("subjects");

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

    public String getMovieIntroduction(String id) {
        String url = "https://api.douban.com/v2/movie/subject/"
                + id + "?apikey=0df993c66c0c636e29ecbb5344252a4a";
        String response = Response.getData(url);
        JSONObject data = JSONObject.parseObject(response, (Type) Object.class);
        return data.getString("summary");
    }
}
