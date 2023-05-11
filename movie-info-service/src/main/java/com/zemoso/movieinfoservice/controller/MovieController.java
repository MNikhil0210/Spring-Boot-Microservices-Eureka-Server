package com.zemoso.movieinfoservice.controller;

import com.zemoso.movieinfoservice.entity.Movie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/movie")
public class MovieController {
    Map<String, String> movieInfo = new HashMap<>(){{
        put("123", "RRR");
        put("456", "KGF");
    }};
    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        return new Movie(movieId, movieInfo.get(movieId));
    }
}
