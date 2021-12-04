package com.netflixmoviematch.service;

import com.netflixmoviematch.model.MovieDto;
import com.netflixmoviematch.webclient.search.MovieSearchClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieSearchService {

    private final MovieSearchClient movieSearchClient;

    public MovieDto getMoviesByRating(int rating) {
        MovieDto response = movieSearchClient.getMoviesByRating(rating);
        log.info(response.toString());
        return response;
    }
}
