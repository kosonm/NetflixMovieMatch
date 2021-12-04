package com.netflixmoviematch.controller;

import com.netflixmoviematch.model.MovieDto;
import com.netflixmoviematch.service.MovieSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MovieSearchController {

    private final MovieSearchService movieSearchService;

    @GetMapping("/movies")
    public MovieDto getMoviesByRating(){
        return movieSearchService.getMoviesByRating(7);
    }
}
