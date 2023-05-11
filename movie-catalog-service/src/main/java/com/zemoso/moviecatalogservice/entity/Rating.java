package com.zemoso.moviecatalogservice.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rating {
    private String movieId;
    private int rating;

    public Rating() {}
    public Rating(String movieId, int rating) {
        this.movieId = movieId;
        this.rating = rating;
    }
}
