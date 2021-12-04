package com.netflixmoviematch.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MovieDto {

    private String title;
    private String synopsis;
    private String year;
    private String imdbRating;

}
