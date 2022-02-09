package com.netflixmoviematch.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MovieRatingDto {

    private String title;
    private String synopsis;
    private String year;
    @JsonProperty("imdbrating")
    private String imdbRating;
    private String img;
}
