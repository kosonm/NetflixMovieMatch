package com.netflixmoviematch.service.impl;

import com.netflixmoviematch.model.movie.MovieDto;
import com.netflixmoviematch.service.MovieSearchService;
import com.netflixmoviematch.webclient.search.MovieSearchClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MovieSearchServiceImpl implements MovieSearchService {

    private final MovieSearchClient movieSearchClient;

    public List<MovieDto> getMovies(Map<String, String[]> searchParams) {
        List<MovieDto> response = movieSearchClient.getMovies(searchParams);
        return response;
    }
}
