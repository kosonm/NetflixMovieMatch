package com.netflixmoviematch.service;

import com.netflixmoviematch.model.movie.MovieDto;

import java.util.List;
import java.util.Map;

public interface MovieSearchService {

    List<MovieDto> getMovies(Map<String, String[]> searchParams);
}
