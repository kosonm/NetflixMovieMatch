package com.netflixmoviematch.model.movie;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MovieDto {

    private String title;
    private String synopsis;
    private String year;
    private String imdbRating;
    private String img;

}
