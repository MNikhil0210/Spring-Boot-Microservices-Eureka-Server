package com.zemoso.moviecatalogservice.controller;

import com.zemoso.moviecatalogservice.entity.CatalogItem;
import com.zemoso.moviecatalogservice.entity.Movie;
import com.zemoso.moviecatalogservice.entity.UserRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/catalog")
public class MovieCatalogController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieCatalogController.class);

    @Autowired
    private WebClient.Builder webClientBuilder;
    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        List<CatalogItem> catalogItems;
        UserRating ratings = webClientBuilder.build()
                .get()
                .uri("http://ratings-data-service/api/rating/users/" + userId)
                .retrieve()
                .bodyToMono(UserRating.class)
                .block();

        catalogItems = ratings.getUserRatings().stream().map(rating -> {
            Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://movie-info-service/api/movie/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();

            return new CatalogItem(movie.getName(), "South Indian Movie", rating.getRating());
        }).collect(Collectors.toList());
        catalogItems.forEach(catalogItem -> {
            LOGGER.info(catalogItem.getName());
            LOGGER.info(catalogItem.getDescription());
            LOGGER.info(String.valueOf(catalogItem.getRating()));
        });
        return catalogItems;
    }
}
