package com.zemoso.moviecatalogservice.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogItem {
    private String name;
    private String description;
    private int rating;

    public CatalogItem(String name, String description, int rating) {
        this.name = name;
        this.description = description;
        this.rating = rating;
    }
}
