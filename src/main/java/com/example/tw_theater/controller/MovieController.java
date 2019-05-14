package com.example.tw_theater.controller;

import com.alibaba.fastjson.JSON;
import com.example.tw_theater.dao.GenreRepository;
import com.example.tw_theater.dao.MovieRepository;
import com.example.tw_theater.model.Genre;
import com.example.tw_theater.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Optional;

@RestController
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private GenreRepository genreRepository;

    private Integer page = 0;
    private Integer size = 10;
    private PageRequest pageRequest = null;

    void setPageRequest(Integer page, Integer size) {
        this.page = page == null ? 0 : page;
        this.size = size == null ? 10 : size;
        this.pageRequest = PageRequest.of(this.page, this.size);
    }

    @GetMapping("/movies")
    String findMovies(String title, String genre, Integer page, Integer size, HttpServletRequest request) {
        setPageRequest(page, size);
        title = title == null ? "" : title;
        String titleLike = "%" + title + "%";

        Page<Movie> movies = null;
        if (genre == null) {
            movies = movieRepository
                    .findByTitleLikeOrOriginalTitleLike(titleLike, titleLike, this.pageRequest);
        } else {
            movies = findByTitleLikeAndGenresContain(title, genre);
        }
        String callback = request.getParameter("callback");
        return callback + "(" + JSON.toJSONString(movies) + ")";
    }

    Page<Movie> findByTitleLikeAndGenresContain(String title, String genreName) {
        Genre genre = genreRepository.findByName(genreName).get();
        Page<Movie> moviePage = movieRepository.findByGenresContains(genre, this.pageRequest);
        Iterator<Movie> movieIterator = moviePage.iterator();
        while (movieIterator.hasNext()) {
            Movie movie = movieIterator.next();
            if (movie.getTitle().contains(title)) {
                movieIterator.remove();
            }
        }
        return moviePage;
    }

    @GetMapping("/movies/{id}")
    String getById(@PathVariable("id") String id, HttpServletRequest request) {
        Optional<Movie> movie = movieRepository.findById(id);
        String callback = request.getParameter("callback");
        return callback + "(" + JSON.toJSONString(movie) + ")";
    }

    @GetMapping("/movies/in_theater")
    String findMoviesIsInTheater(Integer page, Integer size, HttpServletRequest request) {
        setPageRequest(page, size);
        Page<Movie> movies = movieRepository.findByInTheaterIsTrue(this.pageRequest);
        String callback = request.getParameter("callback");
        return callback + "(" + JSON.toJSONString(movies) + ")";
    }
}
