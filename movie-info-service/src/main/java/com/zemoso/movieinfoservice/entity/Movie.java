package com.zemoso.movieinfoservice.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {
    private String movieId;
    private String name;

    public Movie(String movieId, String name) {
        this.movieId = movieId;
        this.name = name;
    }
}
